FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]