# Utiliser une image JDK pour Java 17
FROM eclipse-temurin:17-jdk-jammy AS backend-build

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR construit dans le conteneur
COPY target/*.jar springboot-app.jar

# Exposer le port que Spring Boot utilise (par défaut 8080)
EXPOSE 8090

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "springboot-app.jar"]
