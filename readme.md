# 📌 Atina Project - Java Application with Servlets, Tomcat, and MySQL

## 📖 About the Project  
This project is a Java web application using **Servlets**, **Tomcat 11**, and **MySQL 8** as the database. It allows for connection to a MySQL database and the execution of CRUD operations.

## 🚀 Technologies Used
- **Java 21 (Temurin JDK)**
- **Maven 3.9.6**
- **Apache Tomcat 11**
- **MySQL 8.0**
- **Docker & Docker Compose**

## 📂 Project Structure
```
/my-project
├── Dockerfile
├── docker-compose.yml
├── db-scripts/
│   └── init.sql  # SQL script to create tables
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── api/
│   │   │   │   │   └── UserServlet.java
│   │   │   │   ├── database/
│   │   │   │   │   └── DataBaseConnection.java
│   │   │   │   ├── exceptions/
│   │   │   │   │   └── Status.java
├── pom.xml
```

---

Isso reflete a estrutura de diretórios que você mostrou. Se precisar de mais alguma coisa, é só avisar!

## 🐳 How to Run with Docker
### 📌 1. **Build and Start**
```sh
docker-compose up -d --build
```

### 📌 2. **Access the Application**
- Application URL: [`http://localhost:8080`](http://localhost:8080)
- The application will run on Tomcat and be accessible on port **8080**.

### 📌 3. **Access the Database**
```sh
docker exec -it mysql_container mysql -u root -p
```
Default password: `password`

### 📌 4. **Stop the Containers**
```sh
docker-compose down
```

## 🛠 Manual Setup (Without Docker)
If you want to run it manually without Docker:

### **Database**
1. Install MySQL 8 and create a database named `mydb`.
2. Run the SQL script `db-scripts/init.sql`.

### **Run the Application**
```sh
mvn clean package
java -jar target/yourapp.war
```

## 🔗 Contact  
If you have any questions or suggestions, feel free to reach out!

