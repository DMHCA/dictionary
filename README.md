
# Vocabulary Builder

A personal project aimed at helping to learn and improve English vocabulary through a flexible and scalable backend system.

---

## About the Project

This application is designed as a vocabulary trainer where users can add new words, track progress, and eventually use advanced features like flashcards, spaced repetition, and reminders.

Currently, the project is in early development:

- No user authentication yet (planned for future releases).
- Web frontend is not ready; access is planned either via Telegram bot or later a web interface.
- Runs on AWS EC2 instance, managed with Docker and Docker Compose.
- CI/CD pipeline fully automated via GitHub Actions for build, test, docker image creation, and deployment to AWS.

---

## Features (Planned)

- Add, edit, and delete vocabulary words.
- Flashcards for efficient memorization.
- Spaced repetition algorithm for review scheduling.
- Reminders and notifications.
- Multi-user support with authentication and authorization.
- Web frontend and/or Telegram bot interface.

---

## Technology Stack

- **Java 21 (LTS)** — leveraging latest language features and toolchain.
- **Spring Boot 3.5** — REST API and data access with Spring Data JPA.
- **PostgreSQL** — reliable relational database for data persistence.
- **Docker & Docker Compose** — containerization and deployment.
- **GitHub Actions** — CI/CD pipeline for automated build, test, and deploy.
- **JUnit 5 & Mockito** — testing framework for unit and integration tests.
- **Lombok** — reducing boilerplate code.

---

## Architecture Highlights

- Clean layered architecture (Controller-Service-Repository).
- Validation with Spring Validation annotations.
- Use of Docker images and container registry (GitHub Container Registry).
- Automated deployment with GitHub Actions using SSH to AWS EC2.
- Modular and extensible codebase to support future features like authentication and frontend integration.

---

## How to Run Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/DMHCA/dictionary.git
   cd dictionary
   ```

2. Make sure Docker and Docker Compose are installed.

3. Start PostgreSQL and the app:

   ```bash
   docker-compose up -d
   ./gradlew bootRun
   ```

4. API will be available at `http://localhost:8080`.

---

## Future Plans

- Implement user registration and login.
- Build a React or Vue frontend.
- Develop a Telegram bot for remote vocabulary management.
- Improve spaced repetition algorithm and reminders.
- Add integration and end-to-end tests.

---

## Contact

Roman Trippel — [contact@romantrippel.com](mailto:contact@romantrippel.com)
