# 🌱 Ethical Transparency Portal

A **Spring Boot–based web application** that promotes **ethical product transparency** — helping users view and verify how responsibly a product is sourced, manufactured, and distributed.

This project serves both as a **portfolio project** and a **comprehensive backend reference** covering key Spring Boot and microservices concepts.

---

## 🚀 Features

- 🔹 RESTful APIs for product and transparency data  
- 🔹 Input validation using `@Valid` and custom exception handling  
- 🔹 JWT-based authentication and role-based authorization  
- 🔹 API documentation using **Springdoc OpenAPI (Swagger UI)**  
- 🔹 Database integration with **H2** (dev) and **PostgreSQL** (prod)  
- 🔹 Health checks and metrics with **Spring Boot Actuator**  
- 🔹 Clean layered architecture — Controller → Service → Repository → Entity  
- 🔹 Ready for **Dockerization** and **cloud deployment**  
- 🔹 Follows best practices for maintainability and scalability  

---

## 🧩 Tech Stack

| Category | Technology |
|-----------|-------------|
| **Backend** | Spring Boot 3, Spring Web, Spring Data JPA |
| **Security** | Spring Security, JWT |
| **Validation** | Spring Boot Starter Validation |
| **Database** | H2 (dev), PostgreSQL (prod) |
| **API Docs** | Springdoc OpenAPI (Swagger UI) |
| **Build Tool** | Maven |
| **Testing** | JUnit 5, Mockito |
| **Monitoring** | Spring Boot Actuator |
| **Others** | Lombok, MapStruct (optional), Docker support |

---

## ⚙️ Getting Started

🚀 Ethical Product Transparency Portal — 8 Week Roadmap

Goal: Build a full backend system using Spring Boot + modern tools, while mastering all core interview topics for a 6-year experienced backend developer.
Schedule: ~12 hrs/week (2 hrs/day × 6 days)
Focus: Backend | Microservices | Cloud | CI/CD | Testing

🗓️ Phase 1 — Foundation (Week 1–2)
Week 1: Environment & Setup

 Install JDK 21 (Temurin), Maven, Eclipse

 Create project from Spring Initializr

Dependencies: Spring Web, Spring Boot DevTools, Validation, Lombok, H2 Database

 Configure application.yml (profiles: dev/prod)

 Add .gitignore and push initial commit

 Create sample REST endpoint /health

 Run and verify app on localhost:8080

 Learn: Auto-configuration, Dependency Injection

 Test: basic JUnit test on /health endpoint

Week 2: Database Layer

 Add JPA & Hibernate support

 Create entities:

 Product

 Brand

 TransparencyReport

 Implement JpaRepository interfaces

 Add seed data using import.sql

 Test CRUD operations with H2

 Learn: JPA relationships, transactions

 Write unit tests with Mockito

🗓️ Phase 2 — Business Logic (Week 3–4)
Week 3: Service & Validation

 Create Service layer for Product module

 Add DTOs and request validation annotations

 Implement @ControllerAdvice for global exceptions

 Add custom error responses

 Learn: Bean validation, layered architecture

 Log requests and responses using SLF4J

Week 4: Security & Authentication

 Add spring-boot-starter-security dependency

 Create in-memory users (ADMIN, USER)

 Secure endpoints by role

 Add JWT authentication (optional)

 Learn: JWT filter chain, AuthenticationManager

 Write MockMvc security tests

🗓️ Phase 3 — Advanced Features (Week 5–6)
Week 5: Documentation & Integration

 Add Swagger UI (springdoc-openapi)

 Annotate models and endpoints

 Configure CORS for frontend

 Switch DB to PostgreSQL

 Setup Flyway/Liquibase migrations

 Learn: database versioning

 Test CRUDs via Swagger

Week 6: Monitoring & Quality

 Add Actuator for health and metrics

 Configure custom health indicators

 Add SonarLint checks and fix major issues

 Integrate Jacoco for code coverage

 Increase test coverage to 60%

 Learn: logging patterns, observability basics

🗓️ Phase 4 — Microservices & Deployment (Week 7–8)
Week 7: Microservices & Docker

 Create microservice structure (ProductService, AuthService)

 Add Dockerfile and docker-compose.yml

 Run app + DB containers locally

 Learn: container networking, volumes, ports

 Verify build via docker-compose up

Week 8: CI/CD & Cloud

 Add GitHub Actions workflow for build/test

 Deploy app to Render / Railway / Fly.io

 Configure environment variables securely

 Document all endpoints in README

 Review architecture diagram (mermaid or draw.io)

 Learn: Design patterns (Singleton, Factory, Builder)

 Conduct mock interview recap

✅ Final Deliverables

 REST API live (Swagger documented)

 PostgreSQL + Docker setup complete

 CI/CD with GitHub Actions

 Code coverage ≥ 60%

 Clean SonarLint report

 Detailed README with architecture + endpoints
