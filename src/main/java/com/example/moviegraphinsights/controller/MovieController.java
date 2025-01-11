package com.example.moviegraphinsights.controller;

import com.example.moviegraphinsights.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/actors/most-connected")
    public String getMostConnectedActors() {
        return movieService.getMostConnectedActors();
    }

    @GetMapping("/relations/analysis")
    public String analyzeRelations() {
        return movieService.analyzeRelations();
    }

    @GetMapping("/nodes/analysis")
    public String analyzeNodes() {
        return movieService.analyzeNodes();
    }

    @GetMapping("/complex-query")
    public String runComplexQuery() {
        return movieService.runComplexQuery();
    }
}