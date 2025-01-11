package com.example.moviegraphinsights.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final Driver driver;

    public ActorService(Driver driver) {
        this.driver = driver;
    }

    public String getActorConnections(String actorName) {
        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                String cypherQuery = "MATCH (actor:Person {name: $actorName})-[:ACTED_IN]->(movie:Movie) " +
                        "RETURN movie.title AS movie";
                return tx.run(cypherQuery, org.neo4j.driver.Values.parameters("actorName", actorName))
                        .list()
                        .stream()
                        .map(record -> record.get("movie").asString())
                        .reduce((s1, s2) -> s1 + ", " + s2)
                        .orElse("No movies found for this actor.");
            });
        }
    }
}
