FROM eclipse-temurin:17-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "/app/api-1.0.0-SNAPSHOT.jar"]
