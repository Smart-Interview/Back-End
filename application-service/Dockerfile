FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/application-service-0.0.1-SNAPSHOT.jar /app/application-service.jar
ENTRYPOINT ["java", "-jar", "application-service.jar"]