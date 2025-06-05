# Spring GemFire Performance

This project demonstrates how to perform high-performance data operations using Apache Geode/GemFire with Spring Boot. It is designed to handle 100,000 puts and 10,000 reads in parallel, showcasing the capabilities of GemFire for high-throughput data processing.

## Project Structure

The project is organized as follows:

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

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Setup

1. Clone the repository:
   ```
   git clone <repository-url>
   cd spring-gemfire-performance
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Configure your GemFire server settings in `src/main/resources/application.properties`.

### Running the Application

To run the application, execute the following command:
```
mvn spring-boot:run
```

### Usage

The application will perform 100,000 puts and 10,000 reads in parallel from the GemFire server. Monitor the logs for performance metrics and results.

## Testing

Unit tests are provided in the `src/test/java/com/example/gemfire/SpringGemfirePerformanceApplicationTests.java` file. You can run the tests using:
```
mvn test
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.# springboot-gemfire-sample
