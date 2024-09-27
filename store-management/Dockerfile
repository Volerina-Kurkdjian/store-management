FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rest-docker.jar
ENTRYPOINT ["java","-jar","rest-docker.jar"]