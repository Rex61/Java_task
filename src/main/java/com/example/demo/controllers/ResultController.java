package com.example.demo.controllers;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ResultController {
    @Autowired
    private TestService service;

    @GetMapping("/result")
    public String resultPage (Model model, HttpServletRequest request, HttpServletResponse response) {
        return service.getResult(model, request, response);
    }
}
