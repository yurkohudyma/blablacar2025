# config-service/Dockerfile
FROM eclipse-temurin:19-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/config-service-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
