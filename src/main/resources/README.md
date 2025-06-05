# Spring GemFire Performance Project

This project demonstrates how to perform high-performance data operations using Apache Geode/GemFire with Spring Boot. It is designed to handle a large number of parallel operations efficiently.

## Project Structure

```
spring-gemfire-performance
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── gemfire
│   │   │               ├── SpringGemfirePerformanceApplication.java
│   │   │               ├── config
│   │   │               │   └── GemfireConfig.java
│   │   │               └── service
│   │   │                   └── GemfireService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── README.md
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── gemfire
│                       └── SpringGemfirePerformanceApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**
   Clone this repository to your local machine using:
   ```
   git clone <repository-url>
   ```

2. **Build the Project**
   Navigate to the project directory and build the project using Maven:
   ```
   mvn clean install
   ```

3. **Configure GemFire**
   Update the `application.properties` file with your GemFire server connection details.

4. **Run the Application**
   Start the Spring Boot application using:
   ```
   mvn spring-boot:run
   ```

5. **Performance Testing**
   The `GemfireService` class contains methods to perform 100,000 puts and 10,000 reads in parallel. You can invoke these methods to test the performance of the GemFire operations.

## Dependencies

This project uses the following key dependencies:
- Spring Boot
- Apache Geode/GemFire

Make sure to check the `pom.xml` for the complete list of dependencies.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.