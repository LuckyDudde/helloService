# Multi-stage build — build stage
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests

# Runtime stage — lean image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /build/target/*.jar hello-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hello-service.jar"]