# Use the official Gradle image
FROM eclipse-temurin:21

ADD build/libs/*.jar dockerapp.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "dockerapp.jar" ]