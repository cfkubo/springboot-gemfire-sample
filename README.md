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

- Java 21 
- Maven
- Set Broadcom Maven Repository user credentials See https://gemfire.dev/quickstart/spring


Add Spring Boot for VMware GemFire to a Project
The Spring Boot for VMware GemFire dependencies are available from the Broadcom Support Portal. Access to the Broadcom Maven Repository requires a one-time registration step to create an account.

Spring Boot for VMware GemFire requires users to add the GemFire repository to their projects.

To add Spring Boot for VMware GemFire to a project:

1. You will need a [Broadcom Support Portal](https://support.broadcom.com/) account.

2. Select My Downloads. Search by Product Name = VMware Tanzu GemFire. Click on VMware Tanzu GemFire. Click on VMware Tanzu GemFire. Scroll down, Show All Releases, scroll down to Click Green Token for Repository Access and click on the green symbol to the far right. Note your email address. Copy your access_token (not including any surrounding quotation marks).

3. Add the GemFire repository to your project:
 
Maven: Add the following block to the pom.xml file:
```
<repository>
    <id>gemfire-release-repo</id>
    <name>Broadcom GemFire Release Repository</name>
    <url>https://packages.broadcom.com/artifactory/gemfire/</url>
</repository>
```
4. Add your Broadcom Maven Repository credentials.
Maven: Add the following to the **.m2/settings.xml** file. Replace MY-USERNAME@example and MY-ACCESS-TOKEN with your Broadcom Maven Repository credentials.
```
<settings>
    <servers>
        <server>
            <id>gemfire-release-repo</id>
            <username>MY-USERNAME@example.com</username>
            <password>MY-ACCESS-TOKEN</password>
        </server>
    </servers>
</settings>
```
5. After you have set up the repository and credentials, add the Spring Boot for VMware GemFire dependency to your application.


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
