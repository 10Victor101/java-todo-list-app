# Visão Geral

Este projeto é o back-end de um aplicativo de lista de tarefas (todo list) desenvolvido utilizando Spring Boot como framework, Maven para gerenciamento de dependências e PostgreSQL como banco de dados relacional.

## Requisitos

Necessario ter o Java e o Maven instalados na máquina. <br>

* [Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17)
* [Maven 3.9.5](https://maven.apache.org/download.cgi)
* PostgreSQL

## Configuração do Projeto

1. Clone este repositório para o seu ambiente local.
2. Abra o projeto em sua IDE.
3. Configure as váriaveis de ambiente
4. Configure as credenciais do banco de dados e o secret para o JWT no arquivo application.properties localizado em src/main/resources:
   
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_list
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
api.security.token.secret=seu_secret
```
Ou apenas crie as váriaveis de ambiente

```bash
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
api.security.token.secret=${TOKEN_SECRET}
```

## Como compilar e executar a aplicação

Configure as variaveis de ambiente

Após a instalação dos requisitos: siga os passos abaixo:

1. Clone o repositório.
2. Abra o terminal na pasta raiz do projeto.
3. Execute o comando para iniciar a aplicação:

    ```bash
    mvn spring-boot:run
    ```

## Uso
O back-end do aplicativo de lista de tarefas fornece uma API REST para interagir com as funcionalidades. Foi utilizado o Swagger para documentar e fornecer uma interface interativa. Facilitando o entendimento dos endpoints.

Certifique de que a aplicação esteja em execução antes de tentar acessar o Swagger.

*Swagger UI:*
 http://localhost:8080/swagger-ui.html
