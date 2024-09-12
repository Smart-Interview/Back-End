FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/offer-service-0.0.1-SNAPSHOT.jar /app/offer-service.jar
ENTRYPOINT ["java", "-jar", "offer-service.jar"]
