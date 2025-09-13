# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# POM padre (necesario por el <parent>) + POM del módulo
COPY pom.xml pom.xml
COPY gateway/pom.xml gateway/pom.xml

# Cachear deps SOLO del gateway (sin reactor)
RUN mvn -q -DskipTests -f gateway/pom.xml dependency:go-offline

# Código del gateway y build SOLO del gateway
COPY gateway/src gateway/src
RUN mvn -q -DskipTests -f gateway/pom.xml package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/gateway/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
