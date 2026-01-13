package com.logistics.optimizer.controller;
import com.logistics.optimizer.model.*;
import com.logistics.optimizer.service.LoadOptimizerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/load-optimizer")
public class OptimizerController {
    private final LoadOptimizerService service;
    public OptimizerController(LoadOptimizerService s) { this.service = s; }
    @PostMapping("/optimize")
    public OptimizationResponse optimize(@RequestBody OptimizationRequest r) { return service.solve(r); }
}
