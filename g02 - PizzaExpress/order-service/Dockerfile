# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY order-service/pom.xml order-service/pom.xml

# Cachear deps SOLO de order-service
RUN mvn -q -DskipTests -f order-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY order-service/src order-service/src
RUN mvn -q -DskipTests -f order-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/order-service/target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
