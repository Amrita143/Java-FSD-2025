# Sporty Shoes E-commerce Backend

This directory contains a simple Spring Boot prototype for managing the Sporty Shoes store. It exposes REST endpoints for an administrator to manage products, users and purchases. The application uses an in-memory H2 database so it can be run locally without any setup.

## Features
- Administrator login using basic authentication (default username `admin` and password `admin`).
- CRUD operations for managing products.
- List registered users and simple search by email.
- Record purchases and filter reports by date and category.

## Environment Setup
1. Install **Java 17** or later.
2. Install **Maven 3.8** or later.
3. Clone this repository and navigate to this `Course_3_End_Project` folder.

## Running the Application
```
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

## Useful Endpoints
- `POST /admin/login` – authenticate administrator (basic authentication is enabled by default).
- `GET /admin/products` – list products.
- `POST /admin/products` – add a product.
- `GET /admin/users` – list users with optional `email` search parameter.
- `GET /admin/purchases` – list purchases filtered by optional `date` and `category` parameters.

For a quick test you can use `curl` commands or any REST client such as Postman.

