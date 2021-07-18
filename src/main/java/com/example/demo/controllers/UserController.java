package com.example.demo.controllers;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    UserController(TestService service){
        this.service = service;
    }
    private final TestService service;

    @PostConstruct
    void init(){
        service.getTestsFromDB();
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @PostMapping("/userPost")
    public String userPost (@RequestParam String userData , @RequestParam int testNum , Model model, HttpServletResponse response) {
        return service.setUserData(userData , testNum , model, response);
    }
}
