FROM eclipse-temurin:19-jdk
WORKDIR /app
COPY target/rating-service-0.0.1.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
