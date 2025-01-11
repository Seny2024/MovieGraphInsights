package com.example.moviegraphinsights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MovieGraphController_api {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/actor-connections-ui")
    public String getActorConnections(Model model, String actorName) {
        String url = "http://localhost:8080/actors/connections?actorName=" + actorName;
        String connections = restTemplate.getForObject(url, String.class);
        model.addAttribute("actorName", actorName);
        model.addAttribute("connections", connections);
        return "actor-connections"; // Correspond au fichier Thymeleaf actor-connections.html
    }

    @GetMapping("/most-connected-actors-ui")
    public String getMostConnectedActors(Model model) {
        String url = "http://localhost:8080/actors/most-connected";
        String actors = restTemplate.getForObject(url, String.class);
        model.addAttribute("actors", actors);
        return "most-connected-actors"; // Correspond au fichier Thymeleaf most-connected-actors.html
    }

    @GetMapping("/relations-analysis-ui")
    public String analyzeRelations(Model model) {
        String url = "http://localhost:8080/relations/analysis";
        String relations = restTemplate.getForObject(url, String.class);
        model.addAttribute("relations", relations);
        return "relations-analysis"; // Correspond au fichier Thymeleaf relations-analysis.html
    }

    @GetMapping("/nodes-analysis-ui")
    public String analyzeNodes(Model model) {
        String url = "http://localhost:8080/nodes/analysis";
        String nodes = restTemplate.getForObject(url, String.class);
        model.addAttribute("nodes", nodes);
        return "nodes-analysis"; // Correspond au fichier Thymeleaf nodes-analysis.html
    }

    @GetMapping("/complex-query-ui")
    public String runComplexQuery(Model model) {
        String url = "http://localhost:8080/complex-query";
        String queryResult = restTemplate.getForObject(url, String.class);
        model.addAttribute("queryResult", queryResult);
        return "complex-query"; // Correspond au fichier Thymeleaf complex-query.html
    }
}