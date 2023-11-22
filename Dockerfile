FROM openjdk:17-jdk-alpine

COPY ./docker/target/docker-0.0.1-SNAPSHOT.jar DIGItronik-app.jar

ENTRYPOINT ["java","-jar","/DIGItronik-app.jar"]