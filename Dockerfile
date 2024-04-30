FROM maven:3.8.4-openjdk-17 as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -e

FROM openjdk:17-oraclelinux8

COPY --from=build /app/target/todo-list-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]