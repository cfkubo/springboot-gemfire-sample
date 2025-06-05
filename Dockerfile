FROM eclipse-temurin:21-jre
COPY target/spring-gemfire-performance-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]