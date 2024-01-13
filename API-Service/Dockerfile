FROM maven:3.8.3-openjdk-17 AS build

COPY ./target/API-Service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081

CMD ["java", "-Dspring.profiles.active=DOCKER", "-jar", "app.jar"]