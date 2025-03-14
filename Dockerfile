FROM eclipse-temurin:17-jdk-alpine
  # Lightweight Java 17 base image

WORKDIR /app
  # Working directory inside the container

COPY target/BlogApp-0.0.1-SNAPSHOT.jar app.jar
 # Copy JAR file to container

EXPOSE 8080
  # Expose your application port

CMD ["java", "-jar", "app.jar"]
 # Command to run your app
