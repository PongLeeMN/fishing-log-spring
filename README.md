# FishingLog REST API

A Spring Boot REST API for recording and retrieving fishing catches. This independent learning project focuses on Java, layered backend architecture, HTTP endpoints, JSON request handling, PostgreSQL persistence with Spring Data JPA, and Git workflows.

## Features

- Create fishing records from validated JSON requests
- Persist fishing records in PostgreSQL across application restarts
- Retrieve all recorded fish
- Count fishing records
- Filter fish by species
- Filter fish by lake
- Filter by both species and lake
- Retrieve the top K fish by weight
- Update or delete a fish by its database-generated ID

## Technologies

- Java 26
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Jakarta Bean Validation
- Maven
- Git and GitHub

## Architecture

The application separates responsibilities across three layers:

- **Controller (`FishingController`):** Receives HTTP requests, validates POST request bodies, delegates to the service, and returns responses.
- **Service (`FishingService`):** Coordinates create, read, update, and delete operations and performs filtering and top-K sorting.
- **Repository (`FishingRepository`):** Extends `JpaRepository<Fish, Long>`. Spring Data JPA provides the implementation of `save`, `findAll`, `count`, `findById`, and `deleteById`; Hibernate maps these operations to the configured PostgreSQL database.

The controller and service use constructor injection. `Fish` is a JPA entity with a database-generated `Long` identity ID. Saved records survive application restarts when the same PostgreSQL database is retained.

Filtering and top-K selection currently call `findAll()` and process the results in Java; they are not custom database queries. Counting delegates directly to the repository's `count()` method.

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
| `DELETE` | `/fish/{id}` | Delete a fish by ID; return the deleted record or 404 if absent |
| `PUT` | `/fish/{id}` | Replace an existing fish's fields; return the saved record or 404 if absent |

Species and lake filters are case-insensitive. Query values are trimmed; stored lake values are also trimmed during comparison, but stored species values are not. The combined search requires both parameters.

`/fish/biggest` returns fish in descending weight order. A nonpositive `k` returns an empty list; a `k` larger than the record count returns all records. Other list endpoints do not specify a sort order.

POST returns the saved record with HTTP 200. PUT uses the path ID and replaces the record's fields with the submitted body; it is not a partial update.

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

For a new record, omit `id` so PostgreSQL generates it. POST requires nonblank `species` and `lake` values and a positive `weight` (in pounds); invalid values fail request validation with HTTP 400.

Example response (the generated ID will vary):

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

- JDK 26, with `JAVA_HOME` configured
- A running PostgreSQL server and an existing `fishing_log` database (default connection: `localhost:5432`)
- Database credentials: `DB_PASSWORD` is required; `DB_USERNAME` defaults to `postgres`
- Network access for the Maven wrapper to download Maven and dependencies on the first run; a separate Maven installation is not required

Create the database once, for example by running `CREATE DATABASE fishing_log;` in a PostgreSQL client with an account allowed to create databases. The application user must be able to connect and create/update tables.

Run the commands below from the repository root. Replace the credential placeholders with your local PostgreSQL credentials.

The connection is configured in `src/main/resources/application.properties`. To use a different host, port, or database without editing that file, set `SPRING_DATASOURCE_URL` to the appropriate JDBC URL, such as `jdbc:postgresql://localhost:5432/fishing_log`.

Hibernate's current `spring.jpa.hibernate.ddl-auto=update` setting creates or updates entity tables in the existing database; it does not create the PostgreSQL database itself or recreate tables on every restart. SQL logging is enabled.

### Windows

In PowerShell:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your-local-password"
.\mvnw.cmd spring-boot:run
```

### macOS or Linux

```bash
export DB_USERNAME="postgres"
export DB_PASSWORD="your-local-password"
bash ./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Current Limitations

- Filtering and top-K sorting load all records into memory, and the API does not expose pagination.
- POST uses `@Valid`, but PUT does not perform the same controller-level validation. Entity constraints still exist; validation/error handling is not yet consistent across write endpoints.
- Centralized application error handling has not yet been implemented.
- Automated coverage consists of two Mockito service tests for update behavior; controller and PostgreSQL integration coverage is not yet present.
- Schema changes currently rely on Hibernate's `ddl-auto=update`; there are no versioned database migrations.
- There is no frontend interface, authentication, or per-user ownership of records.

## Planned Improvements

- Make request validation consistent across POST and PUT and add centralized error handling
- Move filtering and top-K selection into database queries and add pagination
- Expand unit tests and add controller and PostgreSQL integration tests
- Introduce versioned database migrations
- Consider a frontend interface
