# MovieGraphInsights - Guide Utilisateur

## Introduction
MovieGraphInsights est une application interactive qui vous permet d'explorer les relations entre les acteurs et les films, ainsi que de mieux comprendre les connexions et tendances dans l'industrie du cinéma. Elle s’appuie sur des technologies modernes et des API backend robustes pour offrir une expérience fluide et intuitive.

---

## Fonctionnalités

### 1. **Explorer les relations entre les acteurs**
- **Ce que cela fait** : Découvrez quels acteurs ont collaboré ensemble sur des films. Vous pouvez rechercher un acteur spécifique et voir ses co-acteurs.
- **Pourquoi c’est utile** : Idéal pour analyser les réseaux de collaborations dans l'industrie cinématographique.

### 2. **Identifier les acteurs les plus connectés**
- **Ce que cela fait** : Trouvez les acteurs qui ont travaillé avec le plus grand nombre de collègues dans leur carrière.
- **Pourquoi c’est utile** : Ces informations permettent d’identifier les figures centrales de l’industrie.

### 3. **Analyser les types de relations**
- **Ce que cela fait** : Montre les différentes relations dans la base de données (par exemple, qui a joué dans quel film, qui a dirigé quel film, etc.).
- **Pourquoi c’est utile** : Comprendre les dynamiques entre les acteurs, les réalisateurs et les films.

### 4. **Explorer les catégories de films et de données**
- **Ce que cela fait** : Analyse les différents types de films, genres ou données liés aux acteurs et réalisateurs.
- **Pourquoi c’est utile** : Obtenez une vue d’ensemble des tendances dans l'industrie cinématographique.

### 5. **Exécuter des recherches complexes**
- **Ce que cela fait** : Permet de poser des questions spécifiques, comme "Quels acteurs ont joué dans des films avec un réalisateur particulier ?" ou "Quels films ont été les plus influents dans une période donnée ?"
- **Pourquoi c’est utile** : Fournit des réponses précises et personnalisées pour des besoins spécifiques.

---

## Outils Utilisés

### Backend
- **Framework** : Spring Boot (Java)
- **Base de données** : Neo4j, une base de données orientée graphe.
- **API** : Une série d'API RESTful développées pour interagir avec la base de données et effectuer des analyses.

### Frontend
- **Technologie** : thymeleaf pour une interface utilisateur interactive et moderne.
- **API Consommées** :
  - `GET /relations/analysis` : Analyse les types de relations.
  - `GET /nodes/analysis` : Analyse les types de nœuds dans la base de données.
  - `GET /complex-query` : Requête complexe pour extraire des informations détaillées.
  - `GET /actors/connections?actorName=<actorName>` : Liste les co-acteurs pour un acteur donné.
  - `GET /actors/most-connected` : Renvoie les acteurs ayant le plus de connexions.

---

## Pourquoi utiliser MovieGraphInsights ?
- **Technologie avancée** : Basée sur Neo4j, qui est conçu pour gérer des données relationnelles complexes.
- **Facilité d’utilisation** : Les API backend font tout le travail technique, permettant au frontend de présenter les résultats de manière claire et accessible.
- **Adaptabilité** : Parfait pour les passionnés de cinéma, les étudiants et les professionnels qui souhaitent explorer ou analyser les tendances du cinéma.

---
