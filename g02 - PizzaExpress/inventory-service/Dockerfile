# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY inventory-service/pom.xml inventory-service/pom.xml

# Cachear deps SOLO de inventory-service
RUN mvn -q -DskipTests -f inventory-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY inventory-service/src inventory-service/src
RUN mvn -q -DskipTests -f inventory-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/inventory-service/target/*.jar /app/app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]
