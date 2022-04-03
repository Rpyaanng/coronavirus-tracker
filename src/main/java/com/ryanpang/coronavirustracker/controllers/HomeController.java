package com.ryanpang.coronavirustracker.controllers;

import java.util.List;

import com.ryanpang.coronavirustracker.models.LocationStats;
import com.ryanpang.coronavirustracker.services.CoronaVirusStatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusStatService coronaVirusStatService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusStatService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
