FROM openjdk:17-alpine
EXPOSE 8089
ADD target/tpAchatProject-1.0.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar", "tpAchatProject.jar"]