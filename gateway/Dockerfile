FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]