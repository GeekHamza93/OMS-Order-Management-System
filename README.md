OMS — Order Management System (Quarkus)

POC d’un Order Management System basé sur Quarkus, pensé pour la rapidité, la faible empreinte mémoire et le déploiement cloud-native (Kubernetes / containers). Focus : API REST, persistance, tests, et build natif.

Pourquoi Quarkus ?
	•	Démarrage ultra-rapide et empreinte mémoire réduite (idéal serverless/Kubernetes)
	•	Extensions prêtes à l’emploi (RESTEasy, Panache, OpenAPI, OIDC…)
	•	Build JVM et natif (GraalVM) dans le même projet
	•	Intégration fluide Docker/Kubernetes

Stack technique
	•	Langage : Java 21
	•	Framework : Quarkus (RESTEasy Reactive, Hibernate ORM Panache, Mutiny)
	•	Base de données : PostgreSQL (prod/dev) / H2 (tests)
	•	Migration : Flyway
	•	Tests : JUnit 5, RestAssured
	•	Documentation : OpenAPI/Swagger UI
	•	CI/CD (exemple) : GitHub Actions (build + tests)

Modules & domaines (MVP)
	•	Orders : CRUD commandes (statut, montant, date)
	•	Customers : CRUD clients (email unique)
	•	(Optionnel) Inventory : à venir (réservation stock, events)

Architecture
	•	API REST → Services applicatifs → Repositories (Panache) → PostgreSQL
	•	Modèle réactif (Mutiny) possible sur endpoints de lecture
	•	Séparation claire : resource (REST), service, repository, domain

Extensions Quarkus (exemples)
quarkus-resteasy-reactive, quarkus-resteasy-reactive-jackson
quarkus-hibernate-orm-panache, quarkus-jdbc-postgresql
quarkus-flyway, quarkus-smallrye-openapi
quarkus-arc, quarkus-oidc (optionnel)
Démarrage rapide

1) Prérequis
	•	Java 21, Maven Wrapper (./mvnw)
	•	Docker (pour DB locale, optionnel mais recommandé)

2) Base de données locale (Docker)
docker run --name oms-postgres -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=oms -p 5432:5432 -d postgres:16

3) Lancer l’application (mode dev)
./mvnw quarkus:dev
•	Swagger UI : http://localhost:8080/q/swagger-ui
	•	Health : http://localhost:8080/q/health

4) Variables de configuration (application.properties)
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/oms

quarkus.hibernate-orm.database.generation=none
quarkus.flyway.migrate-at-start=true

quarkus.http.port=8080

%test.quarkus.datasource.db-kind=h2
%test.quarkus.datasource.jdbc.url=jdbc:h2:mem:oms;DB_CLOSE_DELAY=-1

Tests
./mvnw clean test

Tests unitaires JUnit 5
	•	Tests d’API RestAssured (profil %test avec H2)

Endpoints (MVP)

Customers
	•	GET    /api/customers — liste paginée
	•	POST   /api/customers — créer
	•	GET    /api/customers/{id} — détail
	•	PUT    /api/customers/{id} — mettre à jour
	•	DELETE /api/customers/{id} — supprimer

Orders
	•	GET    /api/orders — liste (filtre: status, customerId)
	•	POST   /api/orders — créer
	•	GET    /api/orders/{id} — détail
	•	PUT    /api/orders/{id} — mettre à jour (ex: status)
	•	DELETE /api/orders/{id} — supprimer

Exemples cURL

# Créer un client
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Alice","lastName":"Martin","email":"alice@example.com"}'

# Créer une commande
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"amount":129.9,"status":"CREATED"}'
OpenAPI / Swagger
	•	Spécification : http://localhost:8080/q/openapi
	•	UI : http://localhost:8080/q/swagger-ui

Docker & exécution

Image locale
./mvnw clean package
docker build -t oms-quarkus:local .
docker run --rm -p 8080:8080 --env-file .env oms-quarkus:local
Docker Compose (DB + app)
version: "3.8"
services:
  db:
    image: postgres:16
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: oms
    ports: ["5432:5432"]
  app:
    image: oms-quarkus:local
    depends_on: [db]
    environment:
      QUARKUS_DATASOURCE_JDBC_URL: jdbc:postgresql://db:5432/oms
      QUARKUS_DATASOURCE_USERNAME: postgres
      QUARKUS_DATASOURCE_PASSWORD: postgres
    ports: ["8080:8080"]
Migrations (Flyway)
	•	Dossier : src/main/resources/db/migration
	•	Exemple : V1__init.sql (tables customers, orders, contraintes)

Build natif (GraalVM)
# Requiert GraalVM ou container buildpack
./mvnw package -Pnative

# Exécution
./target/oms-*-runner

Qualité & CI (suggestion)
	•	GitHub Actions : workflow build.yml (trigger PR/push)
	•	Maven build + tests
	•	(Optionnel) Sonar (analyse qualité)
	•	Ajouter des badges en haut du README (build, coverage)

Roadmap
	•	Ajouter la gestion des stocks
	•	Intégrer Kafka pour l’event-driven
	•	Mettre en place CI/CD GitHub Actions (build + test + analyse Sonar)
	•	Ajouter la sécurité OIDC (Keycloak, JWT)




