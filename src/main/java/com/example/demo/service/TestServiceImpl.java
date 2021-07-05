package com.example.demo.service;
import com.example.demo.domain.Test;
import com.example.demo.domain.TestForm;
import com.example.demo.repository.JpaDataRepo;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class TestServiceImpl implements TestService {
    //TODO:
    @Autowired
    private JpaDataRepo dataRepo;
    private final ArrayList<Test> tests = new ArrayList<Test>();

    public TestServiceImpl() {

    }
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

    @Override
    public void getTestsFromDB() {
        Iterable<Test> r = dataRepo.findAll();
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
            model.addAttribute("points" , findCookie(cookies, "points"));
            model.addAttribute("userData" , findCookie(cookies, "userData"));

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
