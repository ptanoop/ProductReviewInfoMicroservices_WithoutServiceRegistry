FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product.jar
ENTRYPOINT ["java","-jar","/product.jar"]
