
# Coffee Machine API

A Jakarta EE and Spring Boot powered backend application designed to manage coffee brewing processes, recipes, and inventory. This project provides a robust RESTful API for controlling a virtual coffee machine.

## 🚀 Features

- **Brewing Management**: Initiate coffee brewing based on existing recipes.
- **Recipe Management**: Create, list, and delete coffee recipes with specific ingredients.
- **Inventory Tracking**: Monitor and refill ingredients (water, milk, beans, sugar, and cups).
- **Health Monitoring**: Simple endpoint to check system availability.

## 🛠 Tech Stack

- **Java 17/23**
- **Spring Boot 3.3.5** (Web, Data JPA, Validation)
- **Jakarta EE**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL**
- **Lombok**
- **Docker Compose**

## 📋 Prerequisites

- **JDK 17** or higher
- **Maven 3.x**
- **Docker** (for running the database)

## ⚙️ Getting Started

### 1. Database Setup
The project uses PostgreSQL. You can quickly spin up the database using the provided Docker Compose file:
```

bash docker-compose up -d```

### 2. Build and Run
Use the Maven wrapper to start the application:
```

bash ./mvnw spring-boot:run``` 
The API will be available at `http://localhost:8080`.

## 🔌 API Endpoints

☕ Brewing
*   `POST /api/brew` - Brew a coffee by providing a `recipeId`.
    *   *Note: This process automatically deducts ingredients from the inventory.*

### 📖 Recipes
*   `GET /api/recipes` - List all available coffee recipes.
*   `POST /api/recipes` - Create a new recipe (requires name, price, water, milk, beans, and sugar).
*   `GET /api/recipes/{id}` - Get details of a specific recipe.
*   `DELETE /api/recipes/{id}` - Remove a recipe.

### 📦 Inventory
*   `GET /api/inventory` - View current levels of water, milk, beans, sugar, and cups.
*   `POST /api/inventory/refill` - Add supplies to the machine.

### 💓 Health
*   `GET /api/health` - Check if the service is up and running.

## 📁 Project Structure

- `com.school.coffeemachine.controllers`: REST API layer.
- `com.school.coffeemachine.service`: Core business logic (Brewing logic, Inventory management).
- `com.school.coffeemachine.domain`: JPA Entities representing the database schema.
- `com.school.coffeemachine.repository`: Data access layer.

## 🧪 Running Tests
To execute the test suite, run:
```
