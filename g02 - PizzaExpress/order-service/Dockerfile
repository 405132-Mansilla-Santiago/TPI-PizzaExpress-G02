FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Cacheo de dependencias
RUN mvn -q -DskipTests dependency:go-offline

CMD ["mvn", "spring-boot:run"]
