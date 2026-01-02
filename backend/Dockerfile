# Multi-stage build för mindre image size
FROM maven:3.9-eclipse-temurin-17 AS build

# Arbeta i /app
WORKDIR /app

# Kopiera pom.xml först (för bättre cache)
COPY pom.xml .

# Ladda ner dependencies (cachas om pom.xml inte ändras)
RUN mvn dependency:go-offline -B

# Kopiera source code
COPY src ./src

# Bygg JAR-fil
RUN mvn clean package -DskipTests

# Runtime stage - använd standard JRE istället för Alpine
FROM eclipse-temurin:17-jre

WORKDIR /app

# Kopiera JAR från build stage
COPY --from=build /app/target/*.jar app.jar

# Exponera port
EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Kör applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]
