# Etapa de build: compila el proyecto con el wrapper de Gradle y Java 21
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Damos permisos de ejecución al wrapper de Gradle (Linux)
RUN chmod +x ./gradlew

# Construimos el JAR de Spring Boot, sin tests y sin daemon
RUN ./gradlew bootJar -x test --no-daemon

# Etapa final: imagen liviana solo con el JAR
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el JAR generado en la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto donde corre Spring Boot
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
