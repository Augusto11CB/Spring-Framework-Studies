# Effective Automated Testing

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

## System Integration Testing
- Used to validate contracts
- Used to testing specific failure scenarios

[https://phauer.com/2019/modern-best-practices-testing-java/](https://phauer.com/2019/modern-best-practices-testing-java/)

[focus-integration-tests-mock-based-tests/](https://phauer.com/2019/focus-integration-tests-mock-based-tests/)

[https://phauer.com/2016/testing-restful-services-java-best-practices/](https://phauer.com/2016/testing-restful-services-java-best-practices/)

[https://rmannibucau.metawerx.net/post/testcontainers-customized-image](https://rmannibucau.metawerx.net/post/testcontainers-customized-image)

[http://softwaretestingfundamentals.com/integration-testing/#:~:text=INTEGRATION%20TESTING%20is%20a%20level,Definition%20by%20ISTQB](http://softwaretestingfundamentals.com/integration-testing/#:~:text=INTEGRATION%20TESTING%20is%20a%20level,Definition%20by%20ISTQB)

[https://sivalabs.in/2019/10/spring-boot-testing/](https://sivalabs.in/2019/10/spring-boot-testing/)
