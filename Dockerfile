FROM openjdk:17-alpine

COPY build/libs/*.jar /app/

WORKDIR /app

ENTRYPOINT java -jar *.jar
