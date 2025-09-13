# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY supplier-service/pom.xml supplier-service/pom.xml

# Cachear deps SOLO de supplier-service
RUN mvn -q -DskipTests -f supplier-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY supplier-service/src supplier-service/src
RUN mvn -q -DskipTests -f supplier-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/supplier-service/target/*.jar /app/app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/app/app.jar"]
