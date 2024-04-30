FROM maven:3.8.4-jdk-8 as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

COPY --from=build /app/target/todo-list-0.0.1-SNAPSHOT.jar /app/app.jar

FROM openjdk:8-jre-alpine

COPY --from=build /app/target/todo-list-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]