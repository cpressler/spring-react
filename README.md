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

# swagger configuration guidelines are here 
[Spring Boot and Swagger/Ngix Reverse Proxy Howto](https://vkuzel.com/configuration-of-a-nginx-reverse-proxy-in-front-of-a-spring-boot-2-1-application-protected-by-oauth-2-0
)  
The above link can be summarized here.  
In order to reverse proxy the swagger pages behind an nginx frontend you need to do two things
1) modify nginx.conf to forward proxy information
2) setup spring boot application to use forwarded-headers

nginx.conf
```nginx

  # separate out the  port from the host
  # HOST header ( $http_host) can come in like this demo.com:9000 and it will set the port properly in the forwarded requests
  map $http_host $external_port {
    ~*^([A-Za-z0-9\-\.]+):([0-9]+) $2;
    default      '';
  }

    # location of the API to serve off the front end application
    location /api {
      error_log /tmp/api-error.log debug;
      proxy_pass http://localhost:8180/api;
      proxy_set_header   X-Real-IP        $remote_addr;
      proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
      proxy_set_header   Host             $host;
    }
    
    # access to the swagger pages requires a couple 
    location  /swagger-ui.html {
        proxy_pass http://localhost:8180/swagger-ui.html;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Forwarded-Proto $scheme;
        # cannot use this since its not set in the request from the docker host
        #proxy_set_header   X-Forwarded-Port  $server_port;
        proxy_set_header   X-Forwarded-Port  $external_port;
        proxy_set_header   X-Forwarded-Prefix  $http_x_forwarded_prefix;
     }
     
    location /webjars {
        proxy_pass         http://localhost:8180; #change to your port
        proxy_redirect     off;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Forwarded-Proto $scheme;
        # cannot use this since its not set in the request from the docker host
        #proxy_set_header   X-Forwarded-Port  $server_port;
        proxy_set_header   X-Forwarded-Port  $external_port;
        proxy_set_header   X-Forwarded-Prefix  $http_x_forwarded_prefix;
    }     
      
    location /v2 {
        proxy_pass http://localhost:8180/v2;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Forwarded-Proto $scheme;
        # cannot use this since its not set in the request from the docker host
        #proxy_set_header   X-Forwarded-Port  $server_port;
        proxy_set_header   X-Forwarded-Port  $external_port;
        proxy_set_header   X-Forwarded-Prefix  $http_x_forwarded_prefix;
    }
              
    location /swagger-resources {
        proxy_pass http://localhost:8180/swagger-resources;
    }
    
    # if you need access to the actuator endpoints this is needed to fix the urls
    location /actuator {
        proxy_pass http://localhost:8180/actuator;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Forwarded-Proto $scheme;
        # cannot use this since its not set in the request from the docker host
        #proxy_set_header   X-Forwarded-Port  $server_port;
        proxy_set_header   X-Forwarded-Port  $external_port;
        proxy_set_header   X-Forwarded-Prefix  $http_x_forwarded_prefix;
    }
    
    #for the above none of these are needed
    #proxy_set_header   X-Forwarded-Host $host:$server_port;
    #proxy_set_header   X-Real-IP        $remote_addr;
    #proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;

```

spring boot configuration in application.yml
```yaml
server:
  port : ${PORT:8180}
  use-forward-headers: true  # allows handling of nginx reverse proxy
```
spring boot configuration to debug incoming headers to verify proper headers are sent
```yaml
logging:
  path: .
  file: ${logging.path}/react.log
  level:
    root: info
    org:
      springframework:
        web:
          servlet:
            DispatcherServlet: DEBUG
      apache:
        coyote:
          http11:
            Http11InputBuffer: DEBUG
        catalina:
          valves:
            RemoteIpValve: DEBUG

```
need to add this bean to your application file JugToursApplication to allow headers to pass
```java
    @Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilterFilterRegistrationBean() {
        ForwardedHeaderFilter forwardedHeaderFilter = new ForwardedHeaderFilter();
        FilterRegistrationBean<ForwardedHeaderFilter> bean = new FilterRegistrationBean<>(forwardedHeaderFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
```


## NGINX Reverse proxy with dockder containers
https://tutorials.technology/tutorials/30-how-to-use-nginx-reverse-proxy-with-docker.html
## License

Apache 2.0, see [LICENSE](LICENSE).


Remove all containers 
docker rm $(docker ps -a -q) <BR>

Remove all images 
docker rmi $(docker images -q)