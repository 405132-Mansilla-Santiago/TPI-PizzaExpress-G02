# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY delivery-service/pom.xml delivery-service/pom.xml

# Cachear deps SOLO de delivery-service
RUN mvn -q -DskipTests -f delivery-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY delivery-service/src delivery-service/src
RUN mvn -q -DskipTests -f delivery-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/delivery-service/target/*.jar /app/app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]
