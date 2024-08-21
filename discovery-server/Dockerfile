# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/discovery-server-0.0.1-SNAPSHOT.jar /app/discovery-server.jar

# Expose the port Eureka Server will run on
EXPOSE 8761

# Set the profile using an environment variable
ENV SPRING_PROFILES_ACTIVE=prod

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]