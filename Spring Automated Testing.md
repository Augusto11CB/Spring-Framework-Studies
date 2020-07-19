# Spring Automated Testing

**TODO** 
**Configuring Structure**
- [Tntegration and unit separate gradle tasks](https://blog.inspeerity.com/gradle/integration-and-unit-separate-gradle-tasks/)
- [Gradle integration tests](https://spin.atomicobject.com/2018/07/18/gradle-integration-tests/)
- [Getting started with Gradle integration testing](https://www.petrikainulainen.net/programming/gradle/getting-started-with-gradle-integration-testing/)

**Basic Tutorial**
- [Focus on Integration Tests Instead of Mock-Based Tests](https://phauer.com/2019/focus-integration-tests-mock-based-tests/)
- [Testing SpringBoot Applications](https://sivalabs.in/2019/10/spring-boot-testing/)

## Unit Tests - Concepts Overview
- The principal characteristic of a unit tests is that it must **run in isolation.**

- The unit tests **verify business behavior.**
	The bellow snip of code is **NOT** a unit test, since it only validates Java functions. 
 
	```java 
	@Test
	public void testSetNameClient() {
		Client client = new Client();
		order.setOrderNumber("1234");
		assertEquals("1234", orger.getOrderNumber());
	}
	```

	The purpose of Unit tests is not validate java functions like the above case where a value is assigned to a variable, but verify the behavior of the application being written. 

	Getter and setter can be validated, but only if they actually perform some sort of business behavior.

- Unit tests only verify a single scenario

**PS:** `@RunWith` no longer exists; superseded by `@ExtendWith`.

## Mocks - Types Overview

- Dummy mock
The primary purpose of a **dummy mock** is to fullfill a dependency. It is used when the dependency they are doubling for is not checked, perform any behavior, and it does not need be validated if were call.

- Stub
Stubs return a value that fulfills a condition of the test case.  Stub will return a value regardless of the input. It is used when the behavior of the calling code when the dependency returns different values.

- Spy
Used to verify if a dependency has been called.

- Mock
Used to verify the interaction with a dependency

- Fake
Used to simulate business behavior

## Component Integration Test
ares tests that focus on how the application interacts with the libraries it depends upon, as well as testing of cross-cutting concerns (such as logging and security). 

In order to proceed with the component tests maybe there is the necessity to partially or totally initialize the application.

### Goals of Component Integration Test
**Usecase:** Libraries can be updated, to make sure that nothing stopped working after the update Component Integration plays a key role in the application heathy after scenarios like this.
**Usecase:** Test features of cross-cutting concerns such as logging. How to validate if the aspect that was created to log information in both start and end of a method keeps running as expected? Answer: Validate with Component Integration Test.

### RESTful Testing with MockMvc
**MockMVC** class is part of Spring MVC test framework which helps in testing the controllers explicitly starting a Servlet container. With MockMvc, . is possible to test a fully functional REST controller but without the overhead of deploying the app to a container.

MockMVC stands up controller, sends requests and then expects for a response.

### Validating DB Iteractions

### Validating External Libs - Jackson
When Upgrading libs and you want to make sure that nothing is going to brake, is useful to design a set of compoenet integraion test. Spring provides a handy feature called `org.springframework.boot.test.json.JacksonTester` which provides convenient shorthand for marshalling and unmarshalling JSON.

### Validanting Configuration - SpringSecurity 

### Validating Cross-Cutting Features - Logging


## System Integration Testing
> Integration tests determine if independently developed units of software work correctly when they are connected to each other
>  \- Martin Fowler

**Major goes of System Integration Test**
- Verify Correctness
- Document Behavior 
- Detect Regression
- **Used to validate contracts**
	- validate endpoints/addresses
	- validate shapes
	- validate security requirements
- **Used to testing specific failure scenarios**

### TestContainers 
TestContainers is a library for easily use docker containers directly in Junit test. TestContainers make use of Junit `rules`to wire up and incorporate docker containers within Junit test.

`org.testcontainers:testcontainers:{testcontainerVersion}`

Testcontainers make the following kinds of tests easier:
- **Data access layer integration tests**
- **Application integration tests**: for running your application in a short-lived test mode with dependencies, such as databases, message queues or web servers.
- **UI/Acceptance tests**: use containerized web browsers, compatible with Selenium, for conducting automated UI tests.

**TestContainer**

- [MockServerContainer and MockServerClient - Github example](https://github.com/kiview/java-container-lab-bookstore/blob/0e2bab92ba2ddec5403f8809a9a1ee33b638d1ee/src/test/java/com/bee42/javalab/bookstore/BookInventoryRepositoryTest.java)
- [MockServerContainer - Github example](https://github.com/tzolov/twitter2/blob/83aa1f2f3afd51caeeadb3e78e7884fa71f5ba18/spring-cloud-starter-stream-processor-twitter-search/src/test/java/org/springframework/cloud/stream/app/twitter/search/processor/TwitterSearchProcessorIntegrationTests.java)
- [ApplicationContainer](https://github.com/NimG98/draft-guide-reactive-messaging-connector/blob/221903cfb4594e743a413c84906e513ac8976f78/finish/openLibertyCafe/src/test/java/it/io/openliberty/guides/openlibertycafe/AppContainerConfig.java)
- [Exemple of customize a Docker image](https://github.com/testcontainers/testcontainers-java/blob/master/core/src/test/java/org/testcontainers/junit/DockerfileTest.java)
- [How to customize a docker image with Testcontainers](https://rmannibucau.metawerx.net/post/testcontainers-customized-image)

### Spring Cloud Contract
> Spring Cloud Contract is an umbrella project holding solutions that help users in successfully implementing the Consumer Driven Contracts approach.
> \- Spring Docs

By using **Consumer Driven Contracts** approach, the development of applications start from the definition of a contract and all the work is done based in that contract

The problem that Spring Cloud Contract tries to address is, instead of calling the real instances of other services (e.g. by using testcontainer), it allows make calls for stubs. By doing it, the test runs faster and removes all the difficult setup to creating a real scenario.

#### Spring Cloud Contract - DLS Defining Contracts
```groovy 
import org.springframework.cloud.contract.spec.Contract  
import org.springframework.http.HttpHeaders  
import org.springframework.http.MediaType  
  
Contract.make {  
  request {  
  method 'POST'  
  url '/bookings/'  
  body([   
                 "customerId" : $(regex('[0-9]{10}')),  
                 "roomNumber" : $(regex('[0-9]{4}')),  
            "checkIn" : $(regex('[0-9]{2}-[0-9]{2}-[0-9]{4}')),  
            "checkOut" : $(regex('[0-9]{2}-[0-9]{2}-[0-9]{4}'))  
      ])  
      headers {   
 header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)  
      }  
 }  response {  
  status 201  
  headers {   
 header(HttpHeaders.LOCATION, 'bookings/1')  
      }  
 }}
```

#### Example of Spring Cloud Contract - Test Environment
```java
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@AutoConfigureStubRunner
(ids = {"com.ps.bk.hotel:booking-service:+:8081})
@DirtiesContext
public class ValidateBookingContracts {

	@Test
	public void tesSuccessfulBookingCall() {
		...
	}
}
```
