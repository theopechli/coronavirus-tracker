package com.github.theopechli.coronavirustracker.controllers;

import com.github.theopechli.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("allLocationStats", coronavirusDataService.getAllLocationStats());
        model.addAttribute("headers", coronavirusDataService.getHeaders());
        return "home";
    }

}
