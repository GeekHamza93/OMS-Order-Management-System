🧾 OMS – Order Management System
🇫🇷 FR
OMS est une application Java Spring Boot, modulaire et évolutive, conçue pour centraliser, suivre et gérer les commandes de bout en bout.

🇬🇧 EN
OMS is a modular and scalable Java Spring Boot application designed to centralize, track, and manage orders from end to end.

🏗️ Stack technique / Tech Stack
Backend: Java 17, Spring Boot 3, Spring Data JPA, Hibernate

Base de données / Database: PostgreSQL

Sécurité / Security: Spring Security, JWT

Documentation API / API Docs: Swagger / OpenAPI

Tests: JUnit 5, Mockito

DevOps: Docker, Maven

IDE: IntelliJ IDEA

🔧 Fonctionnalités clés / Key Features
🔐 Authentification JWT (admin / utilisateur)
JWT-based authentication (admin / user roles)

📦 Gestion des commandes (CRUD complet)
Full CRUD operations for orders

👥 Gestion des clients et des rôles
Customer and role management

🗃️ Pagination et filtrage avancés
Advanced pagination and filtering

📄 Documentation Swagger intégrée
Integrated Swagger API documentation

🧪 Tests unitaires et d’intégration
Unit and integration tests (mock & real DB)

🚀 Lancer le projet / Getting Started
✅ Prérequis / Prerequisites
Java 17+

Docker

Maven

⚙️ Étapes / Setup Instructions
bash
Copier
Modifier
# 1. Cloner le dépôt / Clone the repository
git clone https://github.com/GeekHamza93/OMS-Order-Management-System.git
cd OMS-Order-Management-System

# 2. Lancer PostgreSQL via Docker
docker-compose up -d

# 3. Démarrer l'application
./mvnw spring-boot:run
📍 Accès Swagger / Swagger UI:
http://localhost:8080/swagger-ui.html

🔐 Authentification JWT / JWT Authentication
POST /api/auth/register → Inscription / Sign up

POST /api/auth/authenticate → Générer un token / Generate token

👉 Ajouter le token dans les en-têtes HTTP :
Include the token in request headers:
Authorization: Bearer <token>

📁 Structure du projet / Project Structure
bash
Copier
Modifier
src/
├── config         # Configuration (sécurité, Swagger, JWT)
├── controller     # Contrôleurs REST / REST controllers
├── dto            # Objets de transfert / DTOs
├── entity         # Entités JPA / JPA entities
├── repository     # Requêtes BDD / Repositories
├── service        # Logique métier / Business logic
├── utils          # Utilitaires / Utilities
✅ Exemple de test / Sample Unit Test
java
Copier
Modifier
@Test
void testCreateOrder() {
    OrderDto order = new OrderDto("ClientA", "ProductX", 3);
    OrderDto savedOrder = orderService.create(order);
    assertNotNull(savedOrder.getId());
}
📌 Roadmap (à venir / Coming Soon)
 Dashboard Angular (front-end)

 Export PDF / Excel

 Intégration de webhooks / Webhook integration

 Notifications par email / Email notifications

🤝 Contributions
FR : Les contributions sont les bienvenues. Merci de soumettre une issue avant toute modification majeure.

EN : Contributions are welcome. Please open an issue before making any major changes.

👤 Auteur / Author
Mohammed-Hamza Attar
🧑‍💻 Freelance Java / Spring Boot / Full-Stack Developer
📫 med.hamza.attar@gmail.com
🔗 Portfolio
