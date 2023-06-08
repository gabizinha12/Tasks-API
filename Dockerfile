FROM maven:3.8.4-eclipse-temurin-17 as build 
WORKDIR /app 
COPY . . 
RUN mvn clean package -DskipTests 
FROM eclipse-temurin:17-jdk
WORKDIR /app 
COPY --from=build ./app/target/*.jar ./app.jar 
ENTRYPOINT java -jar ./app.jar 