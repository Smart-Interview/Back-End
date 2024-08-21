# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the host machine to the working directory in the container
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=native

# Run the JAR file
ENTRYPOINT ["java", "-jar", "myapp.jar"]