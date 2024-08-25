FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]