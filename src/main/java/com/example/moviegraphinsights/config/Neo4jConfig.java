package com.example.moviegraphinsights.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfig {

    @Bean
    public Driver driver() {
        return GraphDatabase.driver("bolt://18.207.138.44",
                AuthTokens.basic("neo4j", "boot-investigation-completion"));
    }
}
