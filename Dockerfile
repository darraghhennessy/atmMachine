# FROM openjdk:16-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:16-jdk-alpine
ADD target/atm.jar atm.jar
ENTRYPOINT ["java", "-jar","atm.jar"]
EXPOSE 8080