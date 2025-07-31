🧾 OMS – Order Management System
FR 🇫🇷
OMS est une application Java Spring Boot modulaire et évolutive, conçue pour centraliser, suivre et gérer les commandes de bout en bout.

EN 🇬🇧
OMS is a modular and scalable Java Spring Boot application designed to centralize, track, and manage orders end to end.

🏗️ Stack technique / Tech Stack
Backend: Java 17, Spring Boot 3, Spring Data JPA, Hibernate

Base de données / Database: PostgreSQL

Sécurité / Security: Spring Security, JWT

Documentation API / API Docs: Swagger / OpenAPI

Tests: JUnit 5, Mockito

DevOps: Docker, Maven

IDE: IntelliJ IDEA

🔧 Fonctionnalités principales / Main Features
🔐 Authentification JWT (admin / utilisateur)
JWT-based authentication (admin / user roles)

📦 Gestion des commandes (CRUD)
Full CRUD for orders

👥 Gestion des clients et des rôles
Customer and role management

🗃️ Pagination & filtrage
Pagination & filtering

📄 Documentation Swagger intégrée
Integrated Swagger API docs

🧪 Tests unitaires et d’intégration
Unit & integration tests (mock & real DB)

🚀 Lancer le projet / Getting Started
Pré-requis / Prerequisites
Java 17+

Docker

Maven

Étapes / Steps
bash
Copier
Modifier
git clone https://github.com/GeekHamza93/OMS-Order-Management-System.git
cd OMS-Order-Management-System

# PostgreSQL avec Docker
docker-compose up -d

# Lancer l'application
./mvnw spring-boot:run
Swagger disponible ici / Swagger UI available at:
http://localhost:8080/swagger-ui.html

🔐 Authentification JWT / JWT Authentication
POST /api/auth/register → inscription / sign up

POST /api/auth/authenticate → obtenir un token / get a token

Ajouter le token dans les headers / Add token to headers:
Authorization: Bearer <token>

📁 Structure du projet / Project Structure
text
Copier
Modifier
src/
├── config         # Config sécurité / Security config
├── controller     # Contrôleurs REST / REST controllers
├── dto            # Objets de transfert / DTOs
├── entity         # Entités JPA / JPA entities
├── repository     # Accès BDD / Repositories
├── service        # Logique métier / Business logic
├── utils          # Outils / Utilities
✅ Exemple de test / Sample Test
java
Copier
Modifier
@Test
void testCreateOrder() {
    OrderDto order = new OrderDto("ClientA", "ProductX", 3);
    OrderDto savedOrder = orderService.create(order);
    assertNotNull(savedOrder.getId());
}
📌 Roadmap (à venir / coming soon)
 Dashboard Angular (front-end)

 Export PDF / Excel

 Webhook pour intégration tiers

 Notifications par email / Email notifications

🤝 Contributions
FR : Les pull requests sont les bienvenues. Merci de créer une issue avant toute modification majeure.
EN : Pull requests are welcome. Please open an issue before making major changes.

👤 Auteur / Author
Mohammed-Hamza Attar
🧑‍💻 Freelance Java / Spring Boot / Full-Stack
📫 med.hamza.attar@gmail.com
🔗 Portfolio
