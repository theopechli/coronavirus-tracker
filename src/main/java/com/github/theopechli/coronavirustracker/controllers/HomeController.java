package com.github.theopechli.coronavirustracker.controllers;

import com.github.theopechli.coronavirustracker.modules.LocationStats;
import com.github.theopechli.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allLocationStats = coronavirusDataService.getAllLocationStats();
        long totalCases = allLocationStats.stream()
                .mapToLong(LocationStats::getTotalCases)
                .sum();
        long totalNewCases = allLocationStats.stream()
                .mapToLong(LocationStats::getDiffFromPrevDay)
                .sum();

        model.addAttribute("allLocationStats", allLocationStats);
        model.addAttribute("totalCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }

}
