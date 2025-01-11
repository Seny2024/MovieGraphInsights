package com.example.moviegraphinsights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieGraphController_graph_api {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/statistics-ui")
    public String showStatistics(@RequestParam(name = "actorName", required = false, defaultValue = "") String actorName, Model model) {
        // Construire l'URL en ajoutant le nom de l'acteur
        String actorUrl = String.format("http://localhost:8080/actors/most-connected?actorName=%s", actorName);

        // Récupération des données d'acteurs, nœuds et relations
        ResponseEntity<String> actorResponse = restTemplate.getForEntity(actorUrl, String.class);
        String actorData = actorResponse.getBody();
        System.out.println("Actor Data: " + actorData); // Log des données brutes

        String nodeUrl = "http://localhost:8080/nodes/analysis";
        ResponseEntity<String> nodeResponse = restTemplate.getForEntity(nodeUrl, String.class);
        String nodeData = nodeResponse.getBody();
        System.out.println("Node Data: " + nodeData); // Log des données brutes

        String relationUrl = "http://localhost:8080/relations/analysis";
        ResponseEntity<String> relationResponse = restTemplate.getForEntity(relationUrl, String.class);
        String relationData = relationResponse.getBody();
        System.out.println("Relation Data: " + relationData); // Log des données brutes

        // Transformation des données pour les graphiques
        List<String> actorNames = new ArrayList<>();
        List<Integer> actorConnections = new ArrayList<>();
        if (actorData != null && !actorData.trim().isEmpty()) {
            String[] actorLines = actorData.split("\n");
            for (String line : actorLines) {
                String[] parts = line.split("\\(");
                if (parts.length == 2) {
                    String actorNameLocal = parts[0].trim(); // Renommer la variable locale
                    int connections = Integer.parseInt(parts[1].replace(")", "").trim());
                    actorNames.add(actorNameLocal);
                    actorConnections.add(connections);
                }
            }
        }

        List<String> nodeNames = new ArrayList<>();
        List<Integer> nodeCounts = new ArrayList<>();
        if (nodeData != null && !nodeData.trim().isEmpty()) {
            String[] nodeLines = nodeData.split("\n");
            for (String line : nodeLines) {
                String[] parts = line.split(", Count: ");
                if (parts.length == 2) {
                    String nodeName = parts[0].replace("Node: [", "").replace("]", "").trim();
                    int count = Integer.parseInt(parts[1].trim());
                    nodeNames.add(nodeName);
                    nodeCounts.add(count);
                }
            }
        }

        List<String> relationNames = new ArrayList<>();
        List<Integer> relationCounts = new ArrayList<>();
        if (relationData != null && !relationData.trim().isEmpty()) {
            String[] relationLines = relationData.split("\n");
            for (String line : relationLines) {
                String[] parts = line.split(", Count: ");
                if (parts.length == 2) {
                    String relationName = parts[0].replace("Relation: ", "").trim();
                    int count = Integer.parseInt(parts[1].trim());
                    relationNames.add(relationName);
                    relationCounts.add(count);
                }
            }
        }

        /* / Log des données pour vérification
        System.out.println("Actor Names: " + actorNames);
        System.out.println("Actor Connections: " + actorConnections);
        System.out.println("Node Names: " + nodeNames);
        System.out.println("Node Counts: " + nodeCounts);
        System.out.println("Relation Names: " + relationNames);
        System.out.println("Relation Counts: " + relationCounts);

        // Ajouter les données au modèle pour Thymeleaf
        model.addAttribute("actorNames", actorNames);
        model.addAttribute("actorConnections", actorConnections);
        model.addAttribute("nodeNames", nodeNames);
        model.addAttribute("nodeCounts", nodeCounts);
        model.addAttribute("relationNames", relationNames);
        model.addAttribute("relationCounts", relationCounts); */

        return "statistics"; // Nom du fichier Thymeleaf
    }
}
