package com.example.moviegraphinsights.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final Driver driver;

    public MovieService(Driver driver) {
        this.driver = driver;
    }

    public String getMostConnectedActors() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String cypherQuery = "MATCH (a:Person)-[:ACTED_IN]->(m:Movie) " +
                        "RETURN a.name AS actor, COUNT(m) AS collaborations ORDER BY collaborations DESC LIMIT 5";
                return tx.run(cypherQuery)
                        .list()
                        .stream()
                        .map(record -> record.get("actor").asString() + " (" + record.get("collaborations").asInt() + ")")
                        .reduce((s1, s2) -> s1 + "\n" + s2)
                        .orElse("No data available.");
            });
        }
    }

    public String analyzeRelations() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String cypherQuery = "MATCH (n)-[r]->(m) RETURN type(r) AS relation, COUNT(*) AS count ORDER BY count DESC";
                return tx.run(cypherQuery)
                        .list()
                        .stream()
                        .map(record -> "Relation: " + record.get("relation").asString() + ", Count: " + record.get("count").asInt())
                        .reduce((s1, s2) -> s1 + "\n" + s2)
                        .orElse("No relationships found.");
            });
        }
    }

    public String analyzeNodes() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String cypherQuery = "MATCH (n) RETURN labels(n) AS labels, COUNT(*) AS count ORDER BY count DESC";
                return tx.run(cypherQuery)
                        .list()
                        .stream()
                        .map(record -> "Node: " + record.get("labels").asList() + ", Count: " + record.get("count").asInt())
                        .reduce((s1, s2) -> s1 + "\n" + s2)
                        .orElse("No nodes found.");
            });
        }
    }

    public String runComplexQuery() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                String cypherQuery = "MATCH (person:Person)-[r:ACTED_IN|DIRECTED|PRODUCED]->(movie:Movie) " +
                        "WHERE person.born IS NOT NULL AND movie.released IS NOT NULL " +
                        "RETURN person.name AS person, movie.title AS movie, type(r) AS role, person.born AS birthYear, movie.released AS releaseYear " +
                        "ORDER BY person.born ASC, movie.released ASC";
                return tx.run(cypherQuery)
                        .list()
                        .stream()
                        .map(record -> "Person: " + record.get("person").asString() + ", Movie: " + record.get("movie").asString() + ", Role: " + record.get("role").asString() + ", Birth Year: " + record.get("birthYear").asInt() + ", Release Year: " + record.get("releaseYear").asInt())
                        .reduce((s1, s2) -> s1 + "\n" + s2)
                        .orElse("No data found.");
            });
        }
    }
}