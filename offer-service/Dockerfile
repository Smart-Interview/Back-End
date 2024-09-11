FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar file into the container
COPY target/JobOfferManagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
