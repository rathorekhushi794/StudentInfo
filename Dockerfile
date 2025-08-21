FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copy the fat jar built by Maven (we set <finalName>app</finalName>)
COPY target/StudentInfo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
