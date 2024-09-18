FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/company-service-0.0.1-SNAPSHOT.jar /app/company-service.jar
ENTRYPOINT ["java", "-jar", "company-service.jar"]

