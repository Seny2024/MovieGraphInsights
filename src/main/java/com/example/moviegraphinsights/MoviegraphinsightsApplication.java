package com.example.moviegraphinsights;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviegraphinsightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviegraphinsightsApplication.class, args);
    }

    @Controller
    public static class HomeController {
        @GetMapping("/")
        public String home() {
            return "index";  // Retourne la page index.jsp
        }
    }

}
