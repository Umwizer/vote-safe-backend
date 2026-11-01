package rw.auca.vote_safe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import rw.auca.vote_safe.services.DashboardServices;

@RestController 
@RequestMapping("api/dashboard")
public class DashboardController {
    private final DashboardServices dashboardServices;

    public DashboardController(DashboardServices dashboardServices) {
        this.dashboardServices = dashboardServices;
    }
    @GetMapping("/summary")
    public ResponseEntity<Map<String,Long>>summary(){
      return ResponseEntity.ok(dashboardServices.getSummary());
    }
}
