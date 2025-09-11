# Dockerfile para microservicio Spring Boot
FROM openjdk:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "target/MovimientoService-0.0.1-SNAPSHOT.jar"]
