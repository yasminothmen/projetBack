package Pfe_Education.mongo.controller;

import Pfe_Education.mongo.repositories.DashboardStatsDTO;
import Pfe_Education.mongo.service.dashboard.DashboardServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    private final DashboardServiceImpl dashboardService;

    public DashboardController(DashboardServiceImpl dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public DashboardStatsDTO getDashboardStats() {
        return dashboardService.getDashboardStats();
    }
}