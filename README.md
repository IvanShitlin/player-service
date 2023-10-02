# Player Service
Player Service provides access to player data stored in a CSV file.

### API
The API provides the following endpoints:

- GET /api/players: Retrieves a list of players.
- GET /api/players/{playerID}: Retrieves a single player.

### Setup
- Clone the repository to your local machine.
- Build the Docker image using the following command:
``` shell
docker build -t player-service .
```
- Start the application using the following command:
``` shell
docker run -p 8080:8080 player-service
```

### Usage

you can access the API endpoints using swagger or curl.

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- curl:

``` shell
curl -X 'GET' \
'http://localhost:8080/api/players?page=0&size=1'
```

``` shell
curl -X 'GET' \
'http://localhost:8080/api/players/{playerID}}'
```

### Used Technologies
- Java 17
- Spring Boot 3.1.4
- Gradle
- H2 Database
- Docker
- Swagger

### Implemented Improvement

I added Pagination to the GET /api/players endpoint to improve performance and reduce the load on the server.
It also allows the client to request a specific page and size of the data.

### Potential Improvements

If more time was given I would consider additional features and improvements:

**Tests:** Create intergrational tests for common cases. Create more tests for edge cases.

**Security:** Implement security features such as authentication and authorization to secure the API endpoints.

**Filtering:** Implement filtering and sorting for the GET /api/players endpoint.

**Optimization:** Transfer to a more efficient database like PostgreSQL or MySQL and add more indexes to the database to improve performance.
Also caching can be used to reduce the load on the server.

**Logging:** Add logging to the application to monitor the application and debug issues.

**Deployment:** Consider using Kubernetes to deploy the application to a cluster. Expose readiness probes with Spring Boot Actuator

**Database management:** Consider using liquibase/flyway to manage database changes and migrations.
