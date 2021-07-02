package com.example.demo.controllers;

import com.example.demo.domain.TestForm;
import com.example.demo.repository.JpaDataRepo;
import com.example.demo.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


// TODO:

@Controller
public class MainController {
    @PostConstruct
    void init(){
        service = new TestServiceImpl(dataRepo);
        service.getTestsFromDB();

    }

    @Autowired
    private JpaDataRepo dataRepo;
    TestServiceImpl service;

    @GetMapping ("/")
    public String mainPage() {
        return "index";
    }
    @GetMapping ("/result")
    public String resultPage (Model model) {
        return service.getResult(model);
    }

    @PostMapping ("/userPost")
    public String userPost (@RequestParam String userData , @RequestParam int testNum , Model model) {
        return service.setUserData(userData , testNum , model);
    }

    @GetMapping("/test")
    public String testPage (Model model) {
        return service.getTest(model);
    }

    @PostMapping("/testPost")
    public String testPost (@ModelAttribute TestForm a , Model model) {
        return service.saveResult(a , model);
    }
}


