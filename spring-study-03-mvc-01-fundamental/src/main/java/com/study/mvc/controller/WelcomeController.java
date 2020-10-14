package com.study.mvc.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        Date today = java.sql.Date.valueOf(LocalDate.now());
        model.addAttribute("today", today);
        return "welcome";
    }
}
