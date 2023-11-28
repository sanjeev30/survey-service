FROM openjdk:17

COPY target/survey-service-0.0.1-SNAPSHOT.jar app.jar

CMD java -jar app.jar

EXPOSE 8080