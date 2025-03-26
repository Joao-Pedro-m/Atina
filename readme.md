# 📌 Projeto Atina - Aplicação Java com Servlets, Tomcat e MySQL

## 📖 Sobre o Projeto
Este projeto é uma aplicação web Java utilizando **Servlets**, **Tomcat 11** e **MySQL 8** como banco de dados. Ele permite a conexão a um banco de dados MySQL e a execução de operações CRUD.

## 🚀 Tecnologias Utilizadas
- **Java 21 (Temurin JDK)**
- **Maven 3.9.6**
- **Apache Tomcat 11**
- **MySQL 8.0**
- **Docker & Docker Compose**

## 📂 Estrutura do Projeto
```
/meu-projeto
├── Dockerfile
├── docker-compose.yml
├── db-scripts/
│   └── init.sql  # Script SQL para criar tabelas
├── src/
│   ├── main/
│   │   ├── java/com/
│   │   │   ├── api/UserServlet.java
│   │   │   ├── database/DataBaseConnection.java
│   │   │   ├── exceptions/Status.java
│   ├── resources/
│   ├── webapp/
│   │   ├── index.jsp
├── pom.xml
```

## 🐳 Como Rodar com Docker
### 📌 1. **Build e Inicialização**
```sh
docker-compose up -d --build
```

### 📌 2. **Acessar a Aplicação**
- URL da aplicação: [`http://localhost:8080`](http://localhost:8080)
- A aplicação será executada no Tomcat e acessível na porta **8080**.

### 📌 3. **Acessar o Banco de Dados**
```sh
docker exec -it mysql_container mysql -u root -p
```
Senha padrão: `password`

### 📌 4. **Derrubar os containers**
```sh
docker-compose down
```

## 🛠 Configuração Manual (Sem Docker)
Caso queira rodar manualmente sem Docker:

### **Banco de Dados**
1. Instale o MySQL 8 e crie um banco chamado `mydb`.
2. Execute o script SQL `db-scripts/init.sql`.

### **Executar a Aplicação**
```sh
mvn clean package
java -jar target/seuapp.war
```

## 🔗 Contato
Caso tenha dúvidas ou sugestões, entre em contato!


