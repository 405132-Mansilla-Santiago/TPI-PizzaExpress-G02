# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre + POM del módulo
COPY pom.xml pom.xml
COPY customer-service/pom.xml customer-service/pom.xml

# Cachear deps SOLO de customer-service
RUN mvn -q -DskipTests -f customer-service/pom.xml dependency:go-offline

# Código del módulo y build SOLO del módulo
COPY customer-service/src customer-service/src
RUN mvn -q -DskipTests -f customer-service/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/customer-service/target/*.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]
