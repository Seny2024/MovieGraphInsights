package com.example.moviegraphinsights.controller;

import com.example.moviegraphinsights.service.ActorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors/connections")
    public String getActorConnections(@RequestParam String actorName) {
        return actorService.getActorConnections(actorName);
    }
}
