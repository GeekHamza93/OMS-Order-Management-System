#  OMS — Order Management System (Quarkus)

##  Description
POC d’un **Order Management System** basé sur **Quarkus**, pensé pour :
- La rapidité
- La faible empreinte mémoire
- Le déploiement **cloud-native** (Kubernetes / containers)

Focus : **API REST, persistance, tests, build natif.**

---

##  Pourquoi Quarkus ?
-  **Démarrage ultra-rapide** et empreinte mémoire réduite (idéal serverless/Kubernetes)
-  **Extensions prêtes à l’emploi** (RESTEasy, Panache, OpenAPI, OIDC…)
-  **Build JVM et natif** (GraalVM) dans le même projet
-  Intégration fluide avec **Docker/Kubernetes**

---

## Stack technique
- **Langage** : Java 21
- **Framework** : Quarkus (RESTEasy Reactive, Hibernate ORM Panache, Mutiny)
- **Base de données** : PostgreSQL (prod/dev) / H2 (tests)
- **Migration** : Flyway
- **Tests** : JUnit 5, RestAssured
- **Documentation** : OpenAPI / Swagger UI
- **CI/CD** : GitHub Actions (build + tests, qualité, déploiement Docker)

---

##  Modules & Domaines (MVP)
- **Customers** : CRUD clients (email unique)
- **Orders** : CRUD commandes (statut, montant, date)
- *(Optionnel à venir)* **Inventory** : gestion du stock, événements

---

##  Architecture
```
API REST → Services applicatifs → Repositories (Panache) → PostgreSQL
```
- Endpoints REST → Services métiers → Repositories
- Modèle réactif (**Mutiny**) possible sur endpoints de lecture
- Séparation claire : **resource / service / repository / domain**

---

##  Extensions Quarkus (exemples)
- `quarkus-resteasy-reactive`
- `quarkus-resteasy-reactive-jackson`
- `quarkus-hibernate-orm-panache`
- `quarkus-jdbc-postgresql`
- `quarkus-flyway`
- `quarkus-smallrye-openapi`
- `quarkus-arc`
- `quarkus-oidc` *(optionnel)*

---

##  Prérequis
- Java 21
- Maven Wrapper (`./mvnw`)
- Docker (pour DB locale recommandé)

---

##  Base de données locale (Docker)
```bash
docker run --name oms-postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=oms \
  -p 5432:5432 -d postgres:16
```

---

## ▶ Lancer l’application (mode dev)
```bash
./mvnw quarkus:dev
```
- Swagger UI : [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)
- Health check : [http://localhost:8080/q/health](http://localhost:8080/q/health)

---

##  Variables de configuration (`application.properties`)
```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/oms

quarkus.hibernate-orm.database.generation=none
quarkus.flyway.migrate-at-start=true

quarkus.http.port=8080

%test.quarkus.datasource.db-kind=h2
%test.quarkus.datasource.jdbc.url=jdbc:h2:mem:oms;DB_CLOSE_DELAY=-1
```

---

##  Tests
- **Unitaires** : JUnit 5
- **API** : RestAssured (profil `%test` avec H2)

Exécution :
```bash
./mvnw clean test
```

---

##  Endpoints (MVP)
### Customers
- `GET /api/customers` — liste paginée
- `POST /api/customers` — créer
- `GET /api/customers/{id}` — détail
- `PUT /api/customers/{id}` — mettre à jour
- `DELETE /api/customers/{id}` — supprimer

### Orders
- `GET /api/orders` — liste (filtre: status, customerId)
- `POST /api/orders` — créer
- `GET /api/orders/{id}` — détail
- `PUT /api/orders/{id}` — mettre à jour (ex: status)
- `DELETE /api/orders/{id}` — supprimer

---

##  Exemples cURL
Créer un client :
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Alice","lastName":"Martin","email":"alice@example.com"}'
```

Créer une commande :
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"amount":129.9,"status":"CREATED"}'
```

---

##  OpenAPI / Swagger
- Spécification : [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)
- UI : [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

---

##  Docker & Exécution
### Image locale
```bash
./mvnw clean package

docker build -t oms-quarkus:local .
docker run --rm -p 8080:8080 --env-file .env oms-quarkus:local
```

### Docker Compose (DB + app)
```yaml
version: "3.8"
services:
  db:
    image: postgres:16
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: oms
    ports:
      - "5432:5432"

  app:
    image: oms-quarkus:local
    depends_on:
      - db
    environment:
      QUARKUS_DATASOURCE_JDBC_URL: jdbc:postgresql://db:5432/oms
      QUARKUS_DATASOURCE_USERNAME: postgres
      QUARKUS_DATASOURCE_PASSWORD: postgres
    ports:
      - "8080:8080"
```

---

##  Migrations (Flyway)
- Dossier : `src/main/resources/db/migration`
- Exemple : `V1__init.sql` (tables `customers`, `orders`, contraintes)

---

##  Build natif (GraalVM)
Requiert **GraalVM** ou container buildpack.
```bash
./mvnw package -Pnative
./target/oms-*-runner
```

---

##  Qualité & CI
### GitHub Actions Workflow (`.github/workflows/build.yml`)
```yaml
name: Build & Test

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-maven

      - name: Build & Test
        run: ./mvnw clean verify -B

      - name: Upload Test Report
        uses: actions/upload-artifact@v3
        with:
          name: test-report
          path: target/surefire-reports

  docker:
    runs-on: ubuntu-latest
    needs: build

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Build Docker image
        run: docker build -t oms-quarkus:ci .
```

### Ajouts possibles
- Analyse **SonarQube/SonarCloud** (Quality Gate)
- Déploiement DockerHub / GHCR
- Badges (build, coverage, quality)

---

##  Roadmap
- [ ] Ajouter la gestion des stocks (Inventory)
- [ ] Intégrer **Kafka** pour l’event-driven
- [ ] CI/CD complet (**GitHub Actions + Sonar + Docker Registry**)
- [ ] Ajouter la sécurité **OIDC** (Keycloak, JWT)
- [ ] Déploiement Helm chart / Kubernetes Manifests

---

##  Auteur
Hamza Attar — *Tech Lead Full-Stack / Java & Quarkus Enthusiast*

---

##  Arborescence proposée
```text
.
├─ .github/
│  └─ workflows/
│     └─ ci.yml
├─ src/
│  ├─ main/
│  │  ├─ java/...
│  │  ├─ resources/
│  │  │  ├─ application.properties
│  │  │  └─ db/migration/
│  │  │     └─ V1__init.sql
│  └─ test/
├─ Dockerfile           # JVM mode
├─ Dockerfile.native    # Native mode (optionnel)
├─ .dockerignore
├─ docker-compose.yml
├─ .env.example
├─ pom.xml
└─ sonar-project.properties (optionnel)
```


## 🧪 CI/CD — GitHub Actions (`.github/workflows/ci.yml`)
```yaml
name: CI - OMS Quarkus

on:
  push:
    branches: [ "**" ]
  pull_request:
    branches: [ "**" ]

jobs:
  build-test:
    runs-on: ubuntu-latest
    permissions:
      contents: read
      packages: write  # pour ghcr.io si on pousse l'image
    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 21
          cache: maven

      - name: Build & Test
        run: ./mvnw -B -ntp clean verify

      - name: Upload Surefire Reports
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: surefire-reports
          path: '**/target/surefire-reports/*'

      - name: Build Docker image (JVM)
        run: |
          docker build -t ghcr.io/${{ github.repository }}/oms:${{ github.sha }} .

      - name: Login to GHCR
        if: github.ref == 'refs/heads/main'
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Push image (main only)
        if: github.ref == 'refs/heads/main'
        run: |
          IMAGE=ghcr.io/${{ github.repository }}/oms
          docker tag $IMAGE:${{ github.sha }} $IMAGE:latest
          docker push $IMAGE:${{ github.sha }}
          docker push $IMAGE:latest

  # Optional: Native build (GraalVM in container)
  native:
    if: github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest
    needs: build-test
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 21
          cache: maven
      - name: Quarkus Native Build (containerized)
        run: ./mvnw -B -DskipTests package -Pnative -Dquarkus.native.container-build=true
      - name: Upload native binary
        uses: actions/upload-artifact@v4
        with:
          name: quarkus-native-runner
          path: target/*-runner
```

> Pour pousser sur **GHCR**, activez le *Packages* dans votre repo et gardez `GITHUB_TOKEN` par défaut. Pour Docker Hub, remplacez le login action et utilisez `secrets.DOCKERHUB_USERNAME` / `secrets.DOCKERHUB_TOKEN`.

---

## 🔍 Qualité — Sonar (optionnel)
### `sonar-project.properties`
```properties
sonar.projectKey=CHANGE_ME_KEY
sonar.organization=CHANGE_ME_ORG
sonar.host.url=https://sonarcloud.io
sonar.java.coveragePlugin=jacoco
sonar.coverage.jacoco.xmlReportPaths=**/target/site/jacoco/jacoco.xml
sonar.sources=src/main/java
sonar.tests=src/test/java
sonar.exclusions=**/generated/**
```

### Job Sonar (optionnel) à ajouter au workflow
```yaml
  sonar:
    runs-on: ubuntu-latest
    needs: build-test
    if: github.event_name != 'pull_request' # exemple: analyse sur push
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 21
          cache: maven
      - name: SonarCloud Scan
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: ./mvnw -B -DskipTests verify sonar:sonar -Dsonar.login=${SONAR_TOKEN}
```

---

## 🐘 Docker Compose (rappel)
> Fichier déjà fourni plus haut, compatible avec l’`.env`.

---

## 🧭 Prochaines étapes
- Créer les secrets nécessaires (`SONAR_TOKEN`, éventuels *Docker Hub* creds).
- Ajuster `sonar-project.properties` (clé projet / organisation).
- Activer GHCR (ou Docker Hub) dans le repo.
- Ajouter des **badges** en haut du README (CI, Coverage, Quality Gate).
