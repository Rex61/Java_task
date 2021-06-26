package com.example.demo.service;

import com.example.demo.domain.TestForm;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.ui.Model;

public interface TestService {
    void getTestsFromDB();

    String getResult(Model model);

    String getTest(Model model);

    String setUserData(String userData , int testNum , Model model);

    String saveResult(TestForm testForm , Model model);
}
