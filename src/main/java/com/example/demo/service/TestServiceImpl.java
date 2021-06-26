package com.example.demo.service;
import com.example.demo.domain.Test;
import com.example.demo.domain.TestForm;
import com.example.demo.repository.JpaDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
//asas
@Service
public class TestServiceImpl implements TestService{

    private boolean testComplete = false;
    private String userData;
    private int testNum;
    private int points;

    private JpaDataRepo dataRepo;
    private final ArrayList<Test> tests = new ArrayList<Test>();

    public TestServiceImpl(JpaDataRepo dataRepo) {
        this.dataRepo = dataRepo;
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
    public String getResult(Model model) {
        if (!testComplete)
            return "redirect:/";
        model.addAttribute("points" , points);
        model.addAttribute("userData" , userData);
        userData = null;
        testComplete = false;
        return "result";
    }

    @Override
    public String getTest(Model model) {
        if (userData == null)
            return "redirect:/";

        model.addAttribute("questions" , tests.get(testNum).getQuestions());
        model.addAttribute("form" , new TestForm());

        return "test";
    }

    @Override
    public String setUserData(String userData, int testNum, Model model) {
        testNum--;
        if ((testNum < 0) || (testNum >= tests.size())){
            return "redirect:/";
        }
        this.testNum = testNum;
        this.userData = userData;

        return "redirect:/test";
    }

    @Override
    public String saveResult(TestForm testForm, Model model) {
        if(userData != null) {
            points = tests.get(testNum).countingPoints(testForm);
            testComplete = true;
            return "redirect:/result";
        }
        return "redirect:/result";
    }
}
