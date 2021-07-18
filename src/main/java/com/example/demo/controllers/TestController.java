package com.example.demo.controllers;

import com.example.demo.domain.TestForm;
import com.example.demo.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {
    TestController(TestService service){
        this.service = service;
    }
    private final TestService service;

    @GetMapping("/test")
    public String testPage (Model model, HttpServletRequest request) {
        return service.getTest(model, request);
    }

    @PostMapping("/testPost")
    public String testPost (@ModelAttribute TestForm testForm , Model model ,
                            HttpServletResponse response, HttpServletRequest request) {
        return service.saveResult(testForm , model , response, request);
    }
}
