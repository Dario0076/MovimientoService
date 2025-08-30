# Dockerfile para microservicio Spring Boot
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
COPY target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
