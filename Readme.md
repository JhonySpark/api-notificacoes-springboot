# Sobre o Projeto

Este é um projeto de teste de proficiência para a Yup Chat, desenvolvido usando Java 17 com o gerenciador de dependências Maven e banco de dados PostgreSQL com Hibernate. Foi criada uma estrutura de tratamento de erros, que poderia ser mais detalhada e completa, mas foi deixada mais resumida apenas para demonstrar o conhecimento sobre tratamento de erros customizados no Spring. Também foi criado um filtro JWT, onde poderíamos trabalhar com vários recursos que o JJWT proporciona, como, por exemplo, níveis de acesso. Todas as rotas são seguras, exceto as rotas de login e registro. Além disso, utilizei o Flyway para gerenciar as migrations e padronizar o gerenciamento do banco, como no projeto Laravel.

Criei o projeto em uma estrutura simples e básica do Spring. Poderia ter criado uma arquitetura mais complexa, mas não achei necessário devido à baixa complexidade do teste._

# Projeto Java - Sistema de Autenticação e Autorização

Este projeto Java implementa um sistema de autenticação e autorização com **JWT**. Ele usa **Spring Boot**, **Maven** como gerenciador de dependências, e integra com banco de dados (MySQL, PostgreSQL) usando **Docker** para facilitar a inicialização do ambiente.

## Requisitos

Certifique-se de ter as seguintes ferramentas instaladas antes de rodar o projeto:

- **Java 17** ou superior
- **Maven 3.6.0** ou superior
- **Docker**  para inicializar o banco de dados via **Docker Compose**

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security com JJWT (Java JWT)**
- **PostgreSQL**
- **Maven**
- **Docker**
- **Hibernate**
- **Flyway - para migrations**

## Configuração do Projeto

### 1. Clone o Repositório

```bash
git clone https://github.com/JhonySpark/api-notificacoes-springboot.git
cd api-notificacoes-springboot
```

### 2. Configuração do Banco de Dados via Docker

Um arquivo `docker-compose.yml` foi fornecido para inicializar o banco de dados. Para iniciar o banco de dados via Docker, execute o seguinte comando:

```bash
docker-compose up -d
```

Isso irá iniciar um contêiner com o banco de dados configurado no Docker. Certifique-se de que o arquivo `application.properties` ou `application.yml` do seu projeto Java esteja configurado corretamente para apontar para o banco de dados que foi inicializado pelo Docker.

Exemplo de configuração para **PostgreSQL**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/YUPCHAT_JAVA
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configurações do Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
server.port=8085
```

> **Nota:** Se estiver usando MySQL, ajuste a `spring.datasource.driver-class-name` para `com.mysql.cj.jdbc.Driver` e modifique o `hibernate.dialect` para `org.hibernate.dialect.MySQL8Dialect`.

### 3. Compilação do Projeto

Com o **Maven** instalado, compile o projeto executando o seguinte comando:

```bash
mvn clean install
```

Este comando irá baixar todas as dependências e compilar o projeto.

### 4. Rodando a Aplicação

Para rodar a aplicação, execute o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação será iniciada em `http://localhost:8085`.

### 5. Endpoints Disponíveis

- `POST /api/auth/register` - Registra um novo usuário
- `POST /api/auth/login` - Autenticação e geração de token JWT
- `GET /api/auth/me` - Retorna informações do usuário autenticado

### 6. Testando o Sistema

Use ferramentas como **Postman** ou **Insomnia** para testar os endpoints da API.
O arquivo JSON das requisições se encontra na raiz do projeto

- `\\Insomnia_java.json`

## Utilização do Docker Compose

O arquivo `docker-compose.yml` está configurado para inicializar o banco de dados postgres no Docker. Certifique-se de que o banco de dados está rodando corretamente antes de iniciar a aplicação.

```bash
docker-compose up -d
```

Esse comando vai levantar o contêiner do banco de dados. Para parar o contêiner, use:

```bash
docker-compose down
```
