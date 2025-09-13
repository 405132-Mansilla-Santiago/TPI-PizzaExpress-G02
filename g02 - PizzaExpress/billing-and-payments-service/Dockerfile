# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY billing-and-payments-service/pom.xml billing-and-payments-service/pom.xml

# Cachear deps SOLO de billing-and-payments-service
RUN mvn -q -DskipTests -f billing-and-payments-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY billing-and-payments-service/src billing-and-payments-service/src
RUN mvn -q -DskipTests -f billing-and-payments-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/billing-and-payments-service/target/*.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
