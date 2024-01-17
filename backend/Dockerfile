# Use the official Gradle image
FROM openjdk:17-jdk-alpine

ADD build/libs/*.jar dockerapp.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "dockerapp.jar" ]

