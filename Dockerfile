# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/StudentInfo-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
