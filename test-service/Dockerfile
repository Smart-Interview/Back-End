FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/test-service-0.0.1-SNAPSHOT.jar /app/test-service.jar
ENTRYPOINT ["java", "-jar", "test-service.jar"]