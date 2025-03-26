
# 1 - Compila o projeto usando Maven com JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# 2 - Definir diretorio de trabalho
WORKDIR /app

# 3 - Copia os arquivos do projeto para o container
COPY . .

# 4 - Compila o projeto e gera o arquivo war
RUN mvn clean package

# 5 - Configura o Tomcat 11 com JRE 21
FROM tomcat:11.0.5-jre21-temurin

# 6 - Remove oo aplicativo padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# 7 - Copia o WAR gerado para o diretorio webapps do Tomcat
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# 8 - Expoe a porta 8080
EXPOSE 8080

# 9 - Inicia o Tomcat
CMD ["catalina.sh","run"]
