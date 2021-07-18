package com.example.demo.service;
import com.example.demo.domain.*;
import com.example.demo.repository.JpaTestRepo;
import com.example.demo.repository.JpaUserIDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {
    //TODO:

    TestServiceImpl(JpaTestRepo testRepo, JpaUserIDRepo userIDRepo){
        this.userIDRepo = userIDRepo;
        this.testRepo = testRepo;
    }

    private final JpaUserIDRepo userIDRepo;
    private final JpaTestRepo testRepo;
    private final List<Test> tests = new ArrayList<Test>();

    private void setCookie (HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    private String findCookie (Cookie []cookies, String name){
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return "";
    }

    private void saveID(String id, String fullName, int points){
        userIDRepo.save(new UserID(id, fullName, points));
    }
    @Override
    public String findUserByID(String id, Model model){
        Iterable<UserID> users = userIDRepo.findAll();
        for (UserID userId : users){
            if(userId.getId().equals(id)){
                model.addAttribute("searchData" , "идентификатор: " + userId.getId()
                        + "<br>ФИО: " + userId.getFullName()
                        + "<br>очки: " + userId.getPoints());
                return "index";
            }
        }
        model.addAttribute("searchData" ,"не найдено совпадений");
        return "index";
    }

    @Override
    public void getTestsFromDB() {
//        ArrayList<String> variants = new ArrayList<>();
//        variants.add("вариант 1");
//        variants.add("вариант 2");
//        variants.add("вариант 3");
//        variants.add("вариант 4");
//        testRepo.save(new Test(new Question("вопрос 1", new Answer(true , false, false, false), variants, 0 , 20)));
        Iterable<Test> r = testRepo.findAll();
        tests.removeAll(tests);
        for (Test t : r ) {
            tests.add(t);
        }
    }

    @Override
    public String getResult(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null)
            return "redirect:/";
        Cookie[] cookies = request.getCookies();

        if (findCookie(cookies, "testComplete").equals("true")){
            setCookie(response, "testComplete", "false", 86400);
        } else {
            return "redirect:/";
        }

        if (! (findCookie(cookies, "points").equals(""))){
            int points = Integer.parseInt(findCookie(cookies, "points"));
            String userData = findCookie(cookies, "userData");

            model.addAttribute("points" , points);
            model.addAttribute("userData" , userData);

            String id = UUID.randomUUID().toString();
            model.addAttribute("id" , id);
            saveID(id, userData, points);

            setCookie(response, "userData", "", 0);
        }
        return "result";
    }

    @Override
    public String getTest(Model model, HttpServletRequest request) {
        if (request.getCookies() == null){
            return "redirect:/result";
        }
        Cookie []cookies = request.getCookies();

        if (findCookie(cookies, "userData").equals(""))
            return "redirect:/";

        int testNum = Integer.parseInt(findCookie(cookies, "testNum"));
        model.addAttribute("questions" , tests.get(testNum).getQuestions());
        model.addAttribute("form" , new TestForm());

        return "test";
    }

    @Override
    public String setUserData(String userData, int testNum, Model model, HttpServletResponse response) {
        testNum--;
        if ((testNum < 0) || (testNum >= tests.size())){
            return "redirect:/";
        }
        setCookie(response, "testNum", String.valueOf(testNum), 86400);
        setCookie(response, "userData", userData, 86400);

        return "redirect:/test";
    }

    @Override
    public String saveResult(TestForm testForm, Model model, HttpServletResponse response, HttpServletRequest request) {
        if (request.getCookies() == null){
            return "redirect:/result";
        }
        Cookie []cookies = request.getCookies();

        if (! (findCookie(cookies, "testNum").equals(""))) {
            int testNum = Integer.parseInt(findCookie(cookies, "testNum"));
            int points = tests.get(testNum).countingPoints(testForm);

            setCookie(response, "points", String.valueOf(points), 86400);
            setCookie(response, "testComplete", "true", 86400);
        }
        return "redirect:/result";
    }
}
