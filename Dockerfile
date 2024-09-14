
# Use Maven base image to build the project
FROM maven:3.8.6-openjdk-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml /app/
RUN mvn dependency:go-offline

# Copy the entire project and build the Spring Boot app
COPY src /app/src
RUN mvn clean package -DskipTests

# Use a slim OpenJDK image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/recime-challenge-0.0.1-SNAPSHOT.jar /app/recime-challenge.jar

# Expose the port on which the application will run
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "recime-challenge.jar"]
