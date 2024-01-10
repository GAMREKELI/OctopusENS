FROM maven:3.8.3-openjdk-17 AS build

COPY ./target/registry-service-0.0.1-SNAPSHOT.jar ./app.jar
#WORKDIR /app
#
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#COPY src ./src
#RUN mvn package -DskipTests
#
#COPY src/main/resources/application.yml /app/src/main/resources/application.yml
#
#FROM openjdk:17
#WORKDIR /app
#COPY --from=build /app/target/registry-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 8761

#CMD ["java", "-Dspring.profiles.active=DOCKER", "-jar", "registry-service-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-Dspring.profiles.active=DOCKER", "-jar", "app.jar"]