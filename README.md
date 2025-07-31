# 🧾 OMS – Order Management System

🇫🇷 FR  
OMS est une application Java Spring Boot, modulaire et évolutive, conçue pour centraliser, suivre et gérer les commandes de bout en bout.

🇬🇧 EN  
OMS is a modular and scalable Java Spring Boot application designed to centralize, track, and manage orders from end to end.

---

## 🏗️ Stack technique / Tech Stack

- **Backend**: Java 17, Spring Boot 3, Spring Data JPA, Hibernate  
- **Base de données / Database**: PostgreSQL  
- **Sécurité / Security**: Spring Security, JWT  
- **Documentation API / API Docs**: Swagger / OpenAPI  
- **Tests**: JUnit 5, Mockito  
- **DevOps**: Docker, Maven  
- **IDE**: IntelliJ IDEA

---

## 🔧 Fonctionnalités clés / Key Features

- 🔐 **Authentification JWT** (admin / utilisateur)  
  *JWT-based authentication (admin / user roles)*

- 📦 **Gestion des commandes** (CRUD complet)  
  *Full CRUD operations for orders*

- 👥 **Gestion des clients et des rôles**  
  *Customer and role management*

- 🗃️ **Pagination et filtrage avancés**  
  *Advanced pagination and filtering*

- 📄 **Documentation Swagger intégrée**  
  *Integrated Swagger API documentation*

- 🧪 **Tests unitaires et d’intégration**  
  *Unit and integration tests (mock & real DB)*

---

## 🚀 Lancer le projet / Getting Started

### ✅ Prérequis / Prerequisites

- Java 17+  
- Docker  
- Maven

### ⚙️ Étapes / Setup Instructions

bash
# 1. Cloner le dépôt / Clone the repository
git clone https://github.com/GeekHamza93/OMS-Order-Management-System.git
cd OMS-Order-Management-System

# 2. Lancer PostgreSQL via Docker
docker-compose up -d

# 3. Démarrer l'application
./mvnw spring-boot:run
