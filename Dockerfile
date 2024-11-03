FROM maven as build
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:17.0
WORKDIR /app
COPY --from=build /app/target/tpAchatProject-1.0.jar /app/
EXPOSE 9090
CMD["java","-jar","tpAchatProject-1.0.jar"]