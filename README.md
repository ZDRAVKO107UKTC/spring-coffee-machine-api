# Coffee Machine API

A Spring Boot backend application designed to manage coffee brewing processes, recipes, and inventory. The API controls a virtual coffee machine, including **payments** (pay in **BGN** or **EUR**, with change returned in **EUR**).

---

## Features

- **Brewing Management**  
  Brew coffee based on a recipe and processed payment.
- **Payments (EUR Base)**  
  - Recipe prices are stored and calculated in **EUR**  
  - Customers can pay in **EUR** or **BGN**  
  - Change is always returned in **EUR**
- **Recipe Management**  
  Create, list, fetch, and delete coffee recipes.
- **Inventory Tracking**  
  View and refill ingredients (water, milk, beans, sugar, cups).
- **Health Monitoring**  
  Endpoint to check system availability.

---

## Tech Stack

- **Java 17 / 23**
- **Spring Boot** (Web, Data JPA, Validation)
- **Jakarta EE** imports
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Lombok**
- **Docker Compose**

---

## Prerequisites

- **JDK 17** or higher  
- **Maven 3.x**  
- **Docker** (optional, for PostgreSQL)

---

## Getting Started

### 1) Database Setup

The project uses PostgreSQL. Start it with Docker Compose:

```bash
docker-compose up -d
```

### 2) Build and Run

```bash
./mvnw spring-boot:run
```

API Base URL:  
```
http://localhost:8080
```

---

## API Endpoints

### Brewing & Payment

**POST** `/api/brew`  
Brew a coffee (deducts ingredients) and processes payment.

#### Request Body (Pay in BGN)
```json
{
  "recipeId": 1,
  "paymentAmount": 5.00,
  "paymentCurrency": "BGN"
}
```

#### Request Body (Pay in EUR)
```json
{
  "recipeId": 1,
  "paymentAmount": 3.00,
  "paymentCurrency": "EUR"
}
```

#### Success Response (Change in EUR)
```json
{
  "status": "SUCCESS",
  "message": "Coffee brewed successfully.",
  "changeEur": 0.06
}
```

#### Possible Errors
- `409 INSUFFICIENT_PAYMENT` — Payment is not sufficient (response includes missing amount in EUR)
- `409 INSUFFICIENT_INVENTORY` — Not enough ingredients or cups
- `400 VALIDATION_ERROR` — Invalid request body

---

### Recipes

- **GET** `/api/recipes` — List all recipes  
- **POST** `/api/recipes` — Create a recipe  
- **GET** `/api/recipes/{id}` — Get recipe details  
- **DELETE** `/api/recipes/{id}` — Delete a recipe  

---

### Inventory

- **GET** `/api/inventory` — View current inventory  
- **POST** `/api/inventory/refill` — Refill inventory  

#### Refill Request Example
```json
{
  "waterMl": 500,
  "milkMl": 200,
  "beansG": 100,
  "sugarG": 50,
  "cups": 10
}
```

---

### Health

- **GET** `/api/health` — Service status

---

## Project Structure

```
com.school.coffeemachine
├── api.controllers     # REST controllers
├── service             # Business logic (brewing, inventory, exchange rates)
├── domain              # JPA entities
├── repository          # Data access layer
└── exception           # API errors and global exception handling
```

---

## Notes

- All recipe prices are stored internally in **EUR**.
- Currency conversion is applied when payments are made in **BGN**.
- Change is always returned in **EUR** for consistency.
- Inventory is validated before brewing to prevent partial transactions.

---

## License

This project is intended for educational use and school assignments.
