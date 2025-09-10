# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos wrapper y POM primero para cachear dependencias
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw -q -DskipTests dependency:go-offline

# Copiamos el código y compilamos
COPY src src
RUN ./mvnw -q -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Flags opcionales (memoria, perfiles, etc.)
ENV JAVA_OPTS=""

# Copiamos el JAR construido (indistinto del nombre)
COPY --from=build /app/target/*.jar /app/app.jar

# (Opcional) Documenta el puerto del servicio
# EXPOSE 8080

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
