FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/devicesManagement-0.0.1-SNAPSHOT.jar device-service.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "device-service.jar"]