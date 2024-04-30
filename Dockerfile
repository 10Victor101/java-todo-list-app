# Use uma imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR da sua aplicação Spring Boot para o contêiner
COPY target/todolist.jar /app/todolist.jar

# Defina o comando de execução da aplicação
CMD ["java", "-jar", "todolistc.jar"]
