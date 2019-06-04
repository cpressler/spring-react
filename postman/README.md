
## Create the Tests in the Postman Application

The [Postman Application](https://www.getpostman.com/downloads/) 
can be used to generate API test cases in manual manner that can then be saved and added to an automated test in Maven
 which can then be run on a build server like Jenkins. The image below shows the UI.
 
![Screenshot](images/postman-test.png)

Save the tests into the Maven project folder **src/test/resources/postman**
 so that it can be run by the automated test environment


##Executing the Tests for Postman

Postman tests can be run with either a docker container or via spring boot plugin.
Below is how to run the test using a spring boot runtime

#### Spring Boot integration tests
This requires no installation of docker to run the tests.
```bash
mvn clean verify -P postman-spring-integration-tests
```
This command will 

1. start up a working API, via spring boot, to be used in the tests
2. Execute the tests
3. stop the working API

#### Docker integration tests
This requires a valid installation of docker to run the tests.
```bash
mvn clean verify -P postman-docker-integration-tests
```
This command will 

1. start up a working API, via docker containers, to be used in the tests
2. Execute the tests
3. stop the working API


