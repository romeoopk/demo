ARG TAG=8-jdk-alpine
FROM openjdk:${TAG} AS JDK_IMAGE
VOLUME /tmp
ADD target/demo-1.0.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
