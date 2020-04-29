# contact-management
This project helps us manage contact details and perform CRUD operations on the available contact details.


## Built With

* 	[Maven](https://maven.apache.org/) - Build Tool(Dependency Management)
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[RESTful web service](https://restfulapi.net/) - Lightweight architectural style to deal with CRUD operations.
* 	[Hibernate](https://hibernate.org/) & [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Hibernate is implementation of JPA and ORM, it provides abstraction over JDBC. Spring Data JPA provides further abstraction over JPA implementations with the help of JPA repositories.
*   [H2](https://www.h2database.com/) - Open-Source lightweight inMemory Relational Database
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Lombok](https://projectlombok.org/) - Space saver and boilerplate code remover.


## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.evolenthealth.contactmanagement.ContactManagementApplication.java` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open IntelliJ IDEA
   - File -> Open -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Run with the help of green arrow(Run Button).

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run OR mvnw spring-boot:run
```

### Security

Spring Boot Starter Security dependency is added to secure REST and actuator endpoints. Username and password for the same is mentioned inside `application.properties` file.

### Actuator

Spring Boot Starter Actuator dependency is added to monitor and manage the application.

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/actuator/health`    	| GET |
|`http://localhost:8080/actuator/info`      	| GET |


### URLs

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/evolenthealth/contacts`                       | GET |
|`http://localhost:8080/evolenthealth/contacts`                       | POST |
|`http://localhost:8080/evolenthealth/contacts/{id}`                  | PUT |
|`http://localhost:8080/evolenthealth/contacts/{id}`                  | DELETE |


## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```
+---.idea
|   \---libraries
+---.mvn
|   \---wrapper
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---evolenthealth
|   |   |           \---contactmanagement
|   |   |               +---config
|   |   |               +---controller
|   |   |               +---dao
|   |   |               +---dto
|   |   |               +---entity
|   |   |               +---exception
|   |   |               +---http
|   |   |               |   +---request
|   |   |               |   \---response
|   |   |               +---service
|   |   |               \---validator
|   |   \---resources
|   |   |   +---application.properties
|   |   |   +---data.sql
|   \---test
|       \---java
|           \---com
|               \---evolenthealth
|                   \---contactmanagement
|                       \---controller
|                           +---controller
|                           \---Service
├── pom.xml
└── README.md

```

## packages

- `config` — to hold security config;
- `controller` — to listen to the client;
- `dao` — to communicate with the database;
- `dto` — to hold model class;
- `entity` — to hold entity class;
- `exception` — to hold exception handling code;
- `http` — to hold request and responses;
- `service` — to hold the business logic;
- `validator` — to hold custom validation logic for Enum -> status;

- `resources/application.properties` - It contains application-wide properties. Spring boot reads the properties defined in this file to configure the application.

- `test/` - contains unit tests

- `pom.xml` - It is an XML representation of project resources, such as project dependencies.
