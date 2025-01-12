package com.example.moviegraphinsights.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActorService {

    private static final Logger logger = LoggerFactory.getLogger(ActorService.class);
    private final Driver driver;

    public ActorService(Driver driver) {
        this.driver = driver;
    }

    public List<Map<String, String>> getActorConnections(String actorName) {
        List<Map<String, String>> results = new ArrayList<>();
        try (Session session = driver.session()) {
            String cypherQuery = "MATCH (actor:Person {name: $actorName})-[r:ACTED_IN]->(movie:Movie)\n" +
                    "RETURN movie.title AS movie, movie.releaseYear AS releaseYear,\n" +
                    "       reduce(s = '', role IN r.roles | s + role + ', ') AS role\n";

            logger.info("Executing query: {} with actorName: {}", cypherQuery, actorName);

            session.writeTransaction(tx -> {
                tx.run(cypherQuery, Values.parameters("actorName", actorName))
                        .list(record -> {
                            Map<String, String> result = new HashMap<>();
                            result.put("Movie", record.get("movie").asString());
                            Value releaseYear = record.get("releaseYear");
                            if (releaseYear != null && !releaseYear.isNull()) {
                                result.put("Release Year", releaseYear.asString());
                            } else {
                                result.put("Release Year", "Unknown");
                            }
                            Value role = record.get("role");
                            if (role != null && !role.isNull()) {
                                result.put("Role", role.asString());
                            } else {
                                result.put("Role", "Unknown");
                            }
                            logger.info("Record: {}", result);
                            results.add(result);
                            return null; // Return null to satisfy the lambda
                        });
                return null; // Return null to satisfy the lambda
            });
        } catch (Exception e) {
            logger.error("Error executing query: {}", e.getMessage(), e);
        }
        logger.info("Query results: {}", results);
        return results;
    }
}
