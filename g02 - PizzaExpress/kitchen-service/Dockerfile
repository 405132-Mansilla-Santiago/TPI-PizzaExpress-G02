# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY kitchen-service/pom.xml kitchen-service/pom.xml

# Cachear deps SOLO de kitchen-service
RUN mvn -q -DskipTests -f kitchen-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY kitchen-service/src kitchen-service/src
RUN mvn -q -DskipTests -f kitchen-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/kitchen-service/target/*.jar /app/app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app/app.jar"]
