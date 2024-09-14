
# RecimeChallenge

A Java-based Spring Boot application for managing trending recipes. This project uses Maven as the build tool, and it is compatible with **Java 21**.

## Features
- Retrieve trending recipes via a REST API.
- Filter recipes by difficulty level.
- Mock external services with **WireMock**.

## Endpoints

1. **Get Trending Recipes**  
   ```http
   GET http://localhost:8080/api/recipes/trending
   ```

   This endpoint retrieves the full list of trending recipes in JSON format.

2. **Get Trending Recipes by Difficulty**  
   ```http
   GET http://localhost:8080/api/recipes/trending/filter?difficulty={easy|medium|hard}
   ```

   This endpoint retrieves trending recipes filtered by difficulty.

## Project Setup

### Prerequisites
- **Java 21** (Ensure you have Java 21 installed)
- **Maven** (To build and run the project)
- **Docker** (To build and run the project in a container)

### Installing and Running the Application Locally

1. Clone the repository:
    ```bash
    git clone https://github.com/giovannicarlos77/recime_challenge.git
    cd recime-challenge
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

### Problems with Lombok

If you encounter issues with **Lombok**, ensure that the Lombok plugin is installed in your IDE. If your IDE does not support Lombok natively, you may need to add the Lombok dependency and ensure it’s correctly installed in your build system.

Install Lombok in your IDE by following the [Lombok Setup Guide](https://projectlombok.org/setup/overview).

### Running the Application with Docker

To run the application with Docker, you can use the Dockerfile which builds the Spring Boot project inside the container:

1. **Build the Docker Image**:
    To build the Docker image, run the following command:
    ```bash
    docker build -t recime-challenge .
    ```

2. **Run the Docker Container**:
    After building the image, run the Docker container:
    ```bash
    docker run -p 8080:8080 recime-challenge
    ```

    This will start the application in a Docker container, and the REST API will be accessible at `http://localhost:8080`.

## Testing the API

You can test the endpoints using **Postman** or **cURL**.

- **Get all trending recipes**:
    ```bash
    curl -X GET http://localhost:8080/api/recipes/trending
    ```

- **Get trending recipes by difficulty**:
    ```bash
    curl -X GET "http://localhost:8080/api/recipes/trending/filter?difficulty=easy"
    ```

## Structure

The project follows the typical Spring Boot structure, with controllers, services, and configurations divided into separate packages:

- `controller`: Contains REST controllers for handling requests.
- `service`: Business logic and external service interaction.
- `model`: Domain objects like `TrendingRecipe`.
- `configuration`: Configuration files for WireMock and other services.
- `resources`: Static resources and `application.properties`.

## Running Tests

To run the unit tests, use the following command:
```bash
mvn test
```

The tests are located in `src/test/java`, with separate test classes for each service and controller.

## Contributing

Feel free to contribute by opening issues or submitting pull requests. Make sure to adhere to the coding standards and write unit tests for new features.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
