# Étape 1 : Construction de l'application
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image d'exécution
FROM openjdk:17.0
WORKDIR /app
COPY --from=build /app/target/tpAchatProject-1.0.jar /app/tpAchatProject.jar
EXPOSE 9090
CMD ["java", "-jar", "tpAchatProject.jar"]
