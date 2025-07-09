FROM eclipse-temurin:19-jdk-alpine
WORKDIR /app
COPY target/telegram-service-0.0.1.jar telegram-service.jar
EXPOSE 9096
ENTRYPOINT ["java", "-jar", "telegram-service.jar"]
