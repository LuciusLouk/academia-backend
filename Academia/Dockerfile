FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Academia-0.0.1.jar
COPY ${JAR_FILE} app_academia.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app_academia.jar" ]