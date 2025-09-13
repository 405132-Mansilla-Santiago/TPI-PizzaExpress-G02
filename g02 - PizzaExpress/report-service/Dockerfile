# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY report-service/pom.xml report-service/pom.xml

# Cachear deps SOLO de report-service
RUN mvn -q -DskipTests -f report-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY report-service/src report-service/src
RUN mvn -q -DskipTests -f report-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/report-service/target/*.jar /app/app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app/app.jar"]
