# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

RUN apt-get update && apt-get install -y postgresql-client

# Set environment variables for PostgreSQL connection
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_NAME=tasks
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

# Run the API
  CMD ["./mvnw", "spring-boot:run"]
