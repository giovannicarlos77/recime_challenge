FROM openjdk:21-jdk-slim AS build

# Install Maven manually
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory for the build stage
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the entire project, including the resources
COPY src /app/src

# Build the Spring Boot app
RUN mvn clean package -DskipTests

# Use a slim OpenJDK image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory for the final container
WORKDIR /app

# Copy the jar file and the `/src` directory from the build stage
COPY --from=build /app/target/recime-challenge-1.0-SNAPSHOT.jar /app/jar/recime-challenge.jar
COPY --from=build /app/src /app/src

# Expose the port on which the application will run
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/jar/recime-challenge.jar"]