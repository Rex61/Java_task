package com.example.demo.controllers;

import com.example.demo.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ResultController {
    ResultController(TestService service){
        this.service = service;
    }
    private final TestService service;

    @GetMapping("/result")
    public String resultPage (Model model, HttpServletRequest request, HttpServletResponse response) {
        return service.getResult(model, request, response);
    }

    @PostMapping("/search")
    public String searchPage (@RequestParam String id,  Model model, HttpServletRequest request, HttpServletResponse response) {
        return service.findUserByID(id, model);
    }
}
