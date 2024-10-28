# First stage: build the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/settings-service-1.0.0-SNAPSHOT.jar settings-service.jar
EXPOSE 9892
ENTRYPOINT ["java", "-jar", "/app/settings-service.jar"]