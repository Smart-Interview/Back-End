FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/report-service-0.0.1-SNAPSHOT.jar /app/report-service.jar
ENTRYPOINT ["java", "-jar", "report-service.jar"]
