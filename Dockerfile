


# Utiliser une image officielle de Java
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans l'image Docker
COPY target/MonEtablissement-1.0-SNAPSHOT.jar /app/app.jar

# Définir la commande de lancement
CMD ["java", "-jar", "/app/app.jar"]

