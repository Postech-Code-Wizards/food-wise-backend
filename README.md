# Foodwise
**Foodwise** is a project developed with **Java JDK 21** and **Spring Boot**. The objective of this application is to provide a platform to help users request/offer meals in a practical and quick way. This project demonstrates the creation of a modern and scalable application with Java, using the latest technologies.

## About the project 
![Foodwise Project Description](https://github.com/userEntity-attachments/assets/ed76d10b-392b-4594-a456-ecc9572e70f0)

## Prerequisites

Before running the project, make sure you have the following tools installed:

- **Java JDK 21** or higher  
  To check the installed Java version, run:
  ```bash
  java -version
  ```

- **Maven**  
  To verify your Maven installation, run:
    ```bash
    mvn -version
    ```

- **Recommended IDE**  
  IntelliJ IDEA or Eclipse

## Docker

- **1. Installation docker and docker compose**  
  Visit for installation: [Docker](https://docs.docker.com/get-docker/)


- **2. Clone the repository**  
  Clone the repository:
    ```bash
    git clone https://github.com/Postech-Code-Wizards/food-wise-backend.git
    ```

- **3. Access the repository**  
  In the terminal, navigate to the root of your project:
    ```bash
    cd food-wise-backend
    ```

- **4. Run the project**  
    ```bash
    docker-compose up -d --build
    ```

The application will be available at the URL: http://localhost:8080

## Installation

- **1. Clone the repository**  
  Clone the repository to your development environment:  
    ```bash
    git clone https://github.com/Postech-Code-Wizards/food-wise-backend.git
    ```

- **2. Compile the project**
  ```bash
  mvn clean install
  ```

- **3. Execute the project**  
  To run the project locally, simply run the corresponding command:
  ```bash
  mvn spring-boot:run
  ```
The application will be available at the URL: http://localhost:8080

API documentation is available at the Swagger URL: http://localhost:8080/swagger-ui/index.html

## Database Configuration
This project uses PostgreSQL as a database. Connection properties are present in the application.properties file, for example:
  ```bash  
  spring.datasource.url=
  spring.datasource.username=
  spring.datasource.password=
  spring.datasource.driver-class-name=
  ```

## Project Structure  

  ```bash
  The project structure follows the pattern:
  
 food-wise-backend/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   └── br/
  │   │   │      └── com/
  │   │   │          └── foodwise/
  │   │   │              └── platform/
  │   │   │                  ├── config/ # App configuration
  │   │   │                  ├── infra/
  │   │   │                      └── security/ # Security and access
  │   │   │                  ├── model/ # Entities and repositories
  │   │   │                      ├── entities/ # Business entities
  │   │   │                      └── repositories/ # Access to database information
  │   │   │                  ├── rest/
  │   │   │                      ├── controller/ # API endpoints
  │   │   │                          ├── exception/ # Exception handling
  │   │   │                          └── handlers/ # Intercepting and handling exceptions
  │   │   │                      ├── converter/ # Access to database information
  │   │   │                          ├── common/ # General converters
  │   │   │                          ├── customer/ # Customer specific converters
  │   │   │                          └── restaurant/ # Restaurant specific converters
  │   │   │                      └── dtos/ # Access to database information
  │   │   │                          ├── request/ # Request object mapping class
  │   │   │                          └── response/ # Response object mapping class
  │   │   │                  ├── service/ #Business logic
  │   │   │                  └── FoodwiseApplication.java # Spring Boot main class
  │   │   └── resources/
  │   │       ├── db/
  │   │           └── migration/ # FlyWay migration files
  │   │       └── application.properties # Spring Boot Settings
  │   └── test/
  │       └── java/
  │           └── br/
  │               └── com/
  │                   └── foodwise/
  │                       └── platform/ # Project testing
  ├── pom.xml # Define Maven dependencies
  ├── docker-compose.yml # Define and orchestrate the required services in Docker containers
  └── DockerFile # Define the Docker image to run the project
  ```

## Tests
### Unit tests 
This project includes unit testing using JUnit to ensure that core functionality is working correctly.

To run the tests, use the following command:
  ```bash
  mvn test
  ```

### Manual tests
Follow through the postman requests.
Everytime a userEntity is updated they are logged out of the system and have to login again.
The userEntity can only update and delete their own profile. This is so that in the future we have ADMINs who are capable of visiting other profiles, but so far, customer and restaurant have regulated access so that we don't expose sensitive data.

## Contribution
Contributions are very welcome! If you would like to contribute to Foodwise, please follow these steps::    

- **1. Fork this repository.**
- **2. Create a branch with your modification (git checkout -b my-modification).**
- **3. Make your changes and commit them (git commit -am 'Modification description').**
- **4. Push your modifications to the repository (git push origin my-modification).**
- **5. Open a Pull Request.**
