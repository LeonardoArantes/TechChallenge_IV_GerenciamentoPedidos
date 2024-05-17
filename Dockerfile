FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /app

COPY . /app/

RUN mvn package

FROM openjdk:17-jdk-slim

COPY target/*.jar app.jar 

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]