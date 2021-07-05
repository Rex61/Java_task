package com.example.demo.controllers;

import com.example.demo.domain.TestForm;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// TODO: remove state from service

@Controller
public class MainController {
    @Autowired
    private TestService service;

    @PostConstruct
    void init(){
        service.getTestsFromDB();
    }

    @GetMapping ("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping ("/result")
    public String resultPage (Model model, HttpServletRequest request, HttpServletResponse response) {
        return service.getResult(model, request, response);
    }

    @PostMapping ("/userPost")
    public String userPost (@RequestParam String userData , @RequestParam int testNum , Model model, HttpServletResponse response) {
        return service.setUserData(userData , testNum , model, response);
    }

    @GetMapping("/test")
    public String testPage (Model model, HttpServletRequest request) {
        return service.getTest(model, request);
    }

    @PostMapping("/testPost")
    public String testPost (@ModelAttribute TestForm a , Model model ,HttpServletResponse response, HttpServletRequest request) {
        return service.saveResult(a , model , response, request);
    }
}


