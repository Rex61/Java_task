package com.example.demo.service;

import com.example.demo.domain.TestForm;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TestService {
    void getTestsFromDB();

    String getResult(Model model, HttpServletRequest request, HttpServletResponse response);

    String getTest(Model model, HttpServletRequest request);

    String setUserData(String userData , int testNum , Model model, HttpServletResponse response);

    String saveResult(TestForm testForm , Model model, HttpServletResponse response, HttpServletRequest request);
}
