# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy


COPY ./target/Tasks-API-0.0.1-SNAPSHOT.jar /app/Tasks-API-0.0.1-SNAPSHOT.jar

# Set the working directory in the container
WORKDIR /app


# Expose the port on which the API will run
EXPOSE 8080

# Start the Java application
CMD ["java", "-jar", "Tasks-API-0.0.1-SNAPSHOT.jar"]
