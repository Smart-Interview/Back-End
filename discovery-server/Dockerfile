FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/discovery-server-0.0.1-SNAPSHOT.jar /app/discovery-server.jar
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]