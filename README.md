# JUG Tours with Spring Boot and React
 
This example app shows how to create a Spring Boot API and CRUD (create, read, update, and delete) its data with a React app.

Please read [Use React and Spring Boot to Build a Simple CRUD App](https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot) to see how this app was created.

**Prerequisites:** [Java 8 Oracle or OpenJDK is preferred](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Node.js 8+](https://nodejs.org/), and [Yarn](https://yarnpkg.com/en/docs/install). You can use npm instead of Yarn, but you'll need to translate the Yarn syntax to npm.

Testing also requires the Selenium Drivers as well [Chrome](https://chromedriver.storage.googleapis.com/index.html?path=74.0.3729.6), [Firefox/Gecko](https://github.com/mozilla/geckodriver/releases)

* [Getting Started](#getting-started)
* [Links](#links)
* [Testing](#testing)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/cpressler/spring-react.git spring-react
cd spring-react
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the server, run:
 
```bash
./mvnw spring-boot:run
```

To run the client, cd into the `app` folder and run:
 
```bash
yarn && yarn start
```

#### Docker image and compose
To build the images for both front end and backend  
```bash
mvn clean install -Pbuild-docker-image  
```


To build the images for both front end and backend  and push to docker hub  
```bash
mvn install -Ppush-docker-image  
```

#### Docker Compose

Run the two containers so the frontend can talke to the server  API
```bash
cd docker
docker compose up    # this will bring up both containers in interactive mode  
```

Go to a browser and type in
localhost:9000  # should bring up the main page.

## Links

This example uses the following open source libraries:

* [React](https://reactjs.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)


## TESTING

### Integration tests of the api using maven-failsafe plugin 
In this testing profile we are embedded our test code into the code base and running the tests against a locally running API server.
This API server is running the code locally and can be tested against other external API servers as well
``` bash  
mvn clean verify -P integration-tests  
```
To override the host to hit the API  
``` bash 
mvn clean verify -P integration-tests -DtestURL=http://localhost:8180 
```
 
### Integration of the API using Docker and Postman Collections and Environments  
Prequisite: Install newman. newman is the command line runner for POSTMAN tests.
``` bash 
% npm install -g newman
```
This will run the default tests against a local running API server that will be running via docker compose.
``` bash 
mvn clean verify -P postman-docker-integration-tests  
```
To override the host to hit by the API. In this project there are 2 environment files.
1. local-dev  - this runs tests against an API instance running on the localhost in a docker container
2. stage      - this is a simulated "stage" environment that also uses the localhost in a docker container 
but it deomonstrates the ability to switch environments on each run on the tests.
``` bash  
mvn clean verify -P docker-integration-tests -Dpostman.env=stage  
```

### Integration Tests using SOAPUI

There is an example of how to use the SOAPUI appplication to run integration tests as well.
These tests can be created in the SOAPUI application and then saved into the proper directory to 
be run by maven using the proper test profile (soapui-integration-tests).

```bash
mvn clean verify -P soapui-integration-tests
```


### To runn ALL the Integration Tests

```bash
% ./src/test/runtests.sh
```

## License

Apache 2.0, see [LICENSE](LICENSE).


Remove all containers 
docker rm $(docker ps -a -q) <BR>

Remove all images 
docker rmi $(docker images -q)