# FishingLog REST API

A Spring Boot REST API for recording and retrieving fishing catches. This independent learning project focuses on Java, layered backend architecture, HTTP endpoints, JSON request handling, and Git workflows.

## Features

- Create fishing records from JSON requests
- Retrieve all recorded fish
- Count fishing records
- Filter fish by species
- Filter fish by lake
- Filter by both species and lake
- Retrieve the top K fish by weight
- Delete a fish by its generated ID

## Technologies

- Java 26
- Spring Boot
- Maven
- Git and GitHub

## Architecture

The application separates responsibilities across three layers:

- **Controller:** Receives HTTP requests and returns responses
- **Service:** Handles filtering, sorting, counting, and application logic
- **Repository:** Stores and retrieves fish records from an in-memory list

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/fish` | Retrieve all fish |
| `GET` | `/fish/count` | Return the number of fish |
| `GET` | `/fish/biggest?k={number}` | Return the top K fish by weight |
| `GET` | `/fish/species?species={species}` | Filter by species |
| `GET` | `/fish/lake?lake={lake}` | Filter by lake |
| `GET` | `/fish/search?species={species}&lake={lake}` | Filter by species and lake |
| `POST` | `/fish` | Create a fish record |
| `DELETE` | `/fish/{id}` | Delete a fish by ID |

## Example POST Request

```http
POST /fish
Content-Type: application/json
```

```json
{
  "species": "Walleye",
  "weight": 5.4,
  "lake": "Lake Superior"
}
```

Example response:

```json
{
  "species": "Walleye",
  "weight": 5.4,
  "lake": "Lake Superior",
  "id": 4
}
```

## Running the Application

### Requirements

- JDK 26

### Windows

```bash
mvnw.cmd spring-boot:run
```

### macOS or Linux

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Current Limitations

- Records are stored in memory and disappear when the application restarts.
- Input validation has not yet been implemented.
- Automated test coverage is currently limited.
- The project does not yet use a database or frontend interface.

## Planned Improvements

- Add request validation and centralized error handling
- Persist records with PostgreSQL
- Add unit and integration tests
- Add update functionality
- Consider a frontend interface
