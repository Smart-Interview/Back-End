# Smart Interview (Interview Question Generator) - Back-End Overview

## Introduction

The **Smart Interview** platform is designed to streamline the interview process by automating the generation of interview questions, evaluating candidate applications, and analyzing CVs using AI models. This project is built using a microservices architecture with **Spring Boot**, **Python**, and **Docker** to ensure scalability, flexibility, and maintainability. The back-end handles tasks like company management, job offers, candidate applications, and test generation, all while integrating with AI models for advanced CV analysis and question generation.

---

## Key Features

1. **Company Management**:
    - A microservice responsible for managing company data like name, address, and sector.
    - Allows recruiters to create and manage their profiles and access job offers.

2. **Job Offer Service**:
    - Handles job offer creation and management.
    - Stores job descriptions and sends the relevant information to other services like the Application Service.

3. **Application Service**:
    - Manages candidate applications.
    - Stores the candidate’s uploaded CV and interacts with the **CV Analysis Service** to match the candidate's qualifications to job requirements.
    - Tracks the application status and updates it after CV analysis.

4. **CV Analysis Service**:
    - This service uses an AI model to extract skills and experiences from candidate CVs.
    - It evaluates the match between the candidate’s CV and the job offer requirements.

5. **Test Management Service**:
    - Manages test creation and the association of tests with candidate applications.
    - Communicates with the **Question Generator Service** to dynamically generate interview questions.

6. **Question Generator Service**:
    - Uses an AI model to generate tailored interview questions based on the job requirements and candidate profile.
    - It returns a set of questions without storing them.

7. **Report Service**:
    - Designed to help HR teams track candidate test performance, pass rates, and scores, aiding in the decision-making process.

---

## Architecture Overview

The architecture is structured as a set of microservices, each with its own responsibility. Key components include:

- **API Gateway**: Handles routing and acts as a centralized access point to all services. It integrates with **Keycloak** to manage authentication and authorization.

- **Spring Boot Microservices**: The back-end is primarily built using Spring Boot for services like **Company Management**, **Job Offer**, **Application**, and **Test Management**.

- **Python AI Services**: AI models for **CV Analysis** and **Question Generation** are exposed as REST APIs using Python.

- **Keycloak Integration**: **Keycloak** is used for user authentication and authorization across all services. The API Gateway communicates with Keycloak to verify user tokens and enforce role-based access control (RBAC).

---

## Technologies

- **Spring Boot**: Used for core back-end services to ensure robust REST API development.
- **Python**: Used in microservices that involve AI models for CV analysis and question generation.
- **PostgreSQL**: The database for storing company, job, application, and test data.
- **Keycloak**: For managing user authentication and authorization via OAuth 2.0.
- **Docker & Docker Compose**: Used to containerize the microservices for easy orchestration and deployment.
- **Eureka**: Service discovery for dynamic management of microservice instances.
- **Spring Cloud Config**: External configuration management for microservices.

---

## Microservices Overview

### 1. API Gateway

- Routes all requests to the appropriate microservice.
- Integrated with Keycloak for authentication and authorization.
- Exposes the following endpoints:
    - `/companies`: For managing companies.
    - `/offers`: For creating and managing job offers.
    - `/applications`: For managing candidate applications.
    - `/tests`: For managing candidate tests.

### 2. Company Management Service

- Manages company and recruiter information.
- **Endpoints**:
    - `POST /companies`: Create a new company.
    - `GET /companies`: Retrieve all companies.

### 3. Job Offer Service

- Handles the creation and management of job offers.
- **Endpoints**:
    - `POST /offers`: Create a new job offer.
    - `GET /offers`: List all job offers.

### 4. Application Service

- Handles candidate applications and CV storage.
- Communicates with the **CV Analysis** service to evaluate candidates.
- **Endpoints**:
    - `POST /applications`: Submit a new application with a CV.
    - `GET /applications/{id}`: Retrieve application status and details.

### 5. CV Analysis Service

- Uses an AI model to extract and evaluate CV content.
- Sends analysis results back to the **Application Service**.
- **Endpoints**:
    - `POST /analyze-cv`: Analyze a submitted CV and return a matching score.

### 6. Test Management Service

- Manages candidate test data.
- Retrieves generated questions from the **Question Generator Service**.
- **Endpoints**:
    - `POST /tests`: Create a new test for a candidate.
    - `GET /tests/{id}`: Retrieve test results and details.

### 7. Question Generator Service

- AI-based question generation for interviews.
- **Endpoints**:
    - `POST /generate-questions`: Generate interview questions for a job offer.

---

## Running the Project

The back-end services are containerized and managed through **Docker Compose**. To run the project locally, follow these steps:

1. Clone the repository.
2. Ensure **Docker** and **Docker Compose** are installed.
3. Navigate to the root directory of the project.
4. Run the following command to start all services:

```bash
docker-compose up --build
```

This command will start all microservices, including the API Gateway, Keycloak, and associated databases.