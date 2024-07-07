# Use a base image with Java runtime environment
FROM openjdk:22-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container at /app
COPY target/acuity-crm.jar app.jar

# Expose the port that your application runs on
EXPOSE 8080

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]