package com.example.moviegraphinsights.controller;

import com.example.moviegraphinsights.service.ActorService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieGraphController_api {

    @Autowired
    private RestTemplate restTemplate;


    private static final Logger logger = LoggerFactory.getLogger(MovieGraphController_api.class);

    @Autowired
    private ActorService actorService;

    @GetMapping("/actor-connections-ui")
    public String showActorConnections(@RequestParam(name = "actorName", required = false, defaultValue = "") String actorName, Model model) {
        // Vérification du nom de l'acteur
        logger.info("Actor Name: {}", actorName);

        // Récupération des données des connexions de l'acteur
        List<Map<String, String>> connectionsResults = actorService.getActorConnections(actorName);

        // Log des résultats
        logger.info("Connections Results: {}", connectionsResults);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("actorName", actorName);
        model.addAttribute("connectionsResults", connectionsResults);

        return "actor-connections"; // Nom du fichier Thymeleaf
    }

    @GetMapping("/most-connected-actors-ui")
    public String showMostConnectedActors(Model model) {
        // Construire l'URL pour la requête des acteurs les plus connectés
        String actorsUrl = "http://localhost:8080/actors/most-connected";

        // Récupération des données des acteurs
        ResponseEntity<String> actorsResponse = restTemplate.getForEntity(actorsUrl, String.class);
        String actorsData = actorsResponse.getBody();
        System.out.println("Actors Data: " + actorsData); // Log des données brutes

        // Transformation des données pour le tableau
        List<Map<String, String>> actorsResults = new ArrayList<>();
        if (actorsData != null && !actorsData.trim().isEmpty()) {
            String[] actorsLines = actorsData.split("\n");
            for (String line : actorsLines) {
                Map<String, String> result = new HashMap<>();
                String[] parts = line.split(" \\(");
                if (parts.length == 2) {
                    String actorName = parts[0].trim();
                    String connections = parts[1].replace(")", "").trim();
                    result.put("Actor", actorName);
                    result.put("Connections", connections);
                }
                actorsResults.add(result);
            }
        }

        // Log des données pour vérification
        System.out.println("Actors Results: " + actorsResults);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("actorsResults", actorsResults);

        return "most-connected-actors"; // Nom du fichier Thymeleaf
    }

    @GetMapping("/relations-analysis-ui")
    public String showRelationsAnalysis(Model model) {
        // Construire l'URL pour la requête des relations
        String relationsUrl = "http://localhost:8080/relations/analysis";

        // Récupération des données des relations
        ResponseEntity<String> relationsResponse = restTemplate.getForEntity(relationsUrl, String.class);
        String relationsData = relationsResponse.getBody();
        System.out.println("Relations Data: " + relationsData); // Log des données brutes

        // Transformation des données pour le tableau
        List<Map<String, String>> relationsResults = new ArrayList<>();
        if (relationsData != null && !relationsData.trim().isEmpty()) {
            String[] relationsLines = relationsData.split("\n");
            for (String line : relationsLines) {
                Map<String, String> result = new HashMap<>();
                String[] parts = line.split(", Count: ");
                if (parts.length == 2) {
                    String relationType = parts[0].replace("Relation: ", "").trim();
                    String count = parts[1].trim();
                    result.put("Relation", relationType);
                    result.put("Count", count);
                }
                relationsResults.add(result);
            }
        }

        // Log des données pour vérification
        System.out.println("Relations Results: " + relationsResults);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("relationsResults", relationsResults);

        return "relations-analysis"; // Nom du fichier Thymeleaf
    }


    @GetMapping("/nodes-analysis-ui")
    public String showNodesAnalysis(Model model) {
        // Construire l'URL pour la requête des nœuds
        String nodesUrl = "http://localhost:8080/nodes/analysis";

        // Récupération des données des nœuds
        ResponseEntity<String> nodesResponse = restTemplate.getForEntity(nodesUrl, String.class);
        String nodesData = nodesResponse.getBody();
        System.out.println("Nodes Data: " + nodesData); // Log des données brutes

        // Transformation des données pour le tableau
        List<Map<String, String>> nodesResults = new ArrayList<>();
        if (nodesData != null && !nodesData.trim().isEmpty()) {
            String[] nodesLines = nodesData.split("\n");
            for (String line : nodesLines) {
                Map<String, String> result = new HashMap<>();
                String[] parts = line.split(", Count: ");
                if (parts.length == 2) {
                    String nodeType = parts[0].replace("Node: [", "").replace("]", "").trim();
                    String count = parts[1].trim();
                    result.put("Node", nodeType);
                    result.put("Count", count);
                }
                nodesResults.add(result);
            }
        }

        // Log des données pour vérification
        System.out.println("Nodes Results: " + nodesResults);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("nodesResults", nodesResults);

        return "nodes-analysis"; // Nom du fichier Thymeleaf
    }


    @GetMapping("/complex-query-ui")
    public String showComplexQueryResults(Model model) {
        // Construire l'URL pour la requête complexe
        String queryUrl = "http://localhost:8080/complex-query";

        // Récupération des données de la requête complexe
        ResponseEntity<String> queryResponse = restTemplate.getForEntity(queryUrl, String.class);
        String queryData = queryResponse.getBody();
        System.out.println("Query Data: " + queryData); // Log des données brutes

        // Transformation des données pour le tableau
        List<Map<String, String>> queryResults = new ArrayList<>();
        if (queryData != null && !queryData.trim().isEmpty()) {
            String[] queryLines = queryData.split("\n");
            for (String line : queryLines) {
                Map<String, String> result = new HashMap<>();
                String[] parts = line.split(", ");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue.length == 2) {
                        result.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }
                queryResults.add(result);
            }
        }

        // Log des données pour vérification
        System.out.println("Query Results: " + queryResults);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("queryResults", queryResults);

        return "complex-query"; // Nom du fichier Thymeleaf
    }
}