# Construção da aplicação com Maven
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY mvnw . 
COPY .mvn .mvn 
COPY pom.xml . 

# copia arquivos de configuração maven
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

# Copia a pasta src para dentro de uma pasta src criada forçadamente
COPY src src

# Criando o JAR, e -X para debugar
RUN ./mvnw package -DskipTests -X

# Execução com Amazon Corretto 21 (jdk21)
FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

# Copia o JAR construído para o diretório /app
COPY --from=build /app/target/*.jar /app/app.jar

# Executa o JAR com o comando Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

