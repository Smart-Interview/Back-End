FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/candidate-service-0.0.1-SNAPSHOT.jar /app/candidate-service.jar
ENTRYPOINT ["java", "-jar", "candidate-service.jar"]