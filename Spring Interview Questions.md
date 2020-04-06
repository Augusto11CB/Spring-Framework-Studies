


## Spring Vs Spring Boot
* **Spring Framework** aims to simplify Java Applications Development.
* **Spring Boot Framework** aims to simplify Spring Development.

## What is Spring Boot and Its Purposes
**Spring Boot Framework** is:
Auto-Dependency Resolution (Spring Boot Starter), 
Auto-Configuration (Spring Boot Auto-Configurator), 
Management EndPoints (Spring Boot Actuator),
Embedded HTTP Servers(Jetty/Tomcat etc.),
 Spring Boot CLI

## Advantages of Spring Boot
* auto-configuration -
* Spring boot follows “Opinionated Defaults Configuration” Approach, which means that it helps you to setup a fully working application(web app or otherwise) very quickly by providing you intelligent default configurations that you are most likely to be satisfied to start with. Also, iit can be overrided in order to atend the needs of some requeriment.

## Spring Boot and Transitively Dependency Resolution Management by Gradle/Maven
**What is “Transitively Dependency Resolution Management”?**  These are dependencies that are dependencies of your direct dependencies.

## Spring Boot Starter Dependency
Spring Boot starters are a set of dependency management providers. Spring Boot aggregates common dependencies together. All the available starters come under the org.springframework.boot group.

## What is auto-configuration in Spring Boot and How to disable the  auto-configuration?

## Explain Spring Actuator
## Explain Spring Data

## Can we create a non-web application in Spring Boot?
Yes, we can create a non-web application by removing the web dependencies from the classpath along with changing the way Spring Boot creates the application context.

## How is Hibernate chosen as the default implementation for JPA without any configuration?
When we use the **Spring Boot Auto Configuration,** automatically the **spring-boot-starter-data-jpa** dependency gets added to the pom.xml file. Now, since this dependency has a transitive dependency on JPA and Hibernate, Spring Boot automatically auto-configures Hibernate as the default implementation for JPA

## What is Spring Data REST?
### How does path=”sample”, collectionResourceRel=”sample” work with Spring Data Rest

## In which layer, should the boundary of a transaction start?
The boundary of the transaction should start from the Service Layer since the logic for the business transaction is present in this layer itself.

## Why is Spring Data REST not recommended in real-world applications?
Spring Data REST is not recommended in real-world applications as you are exposing your database entities directly as REST Services.

## What does REST stand for?

## What is a Resource?

##  What are idempotent operations? Why is idempotency important?
// TODO https://javarevisited.blogspot.com/2016/05/what-are-idempotent-and-safe-methods-of-HTTP-and-REST.html

## What is Spring Cloud?
According to the official website of Spring Cloud, Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, leadership election, distributed sessions, cluster state)

### Problems solved by Spring Cloud
The issues which are solved by Spring Cloud are: 

-   **The complexity associated with distributed systems –** This includes network issues, Latency overhead, Bandwidth issues, security issues.
-   **Ability to handle Service Discovery –** Service discovery allows processes and services in a cluster to find each other and communicate.
-   **Solved redundancy issues –** Redundancy issues often occur in distributed systems.
-   **Load balancing** **–** Improves the distribution of workloads across multiple computing resources, such as a computer cluster, network links, central processing units.
-   **Reduces performance issues –** Reduces performance issues due to various operational overheads.

## References
[Edureka Spring Boot Interview Questions](https://www.edureka.co/blog/interview-questions/spring-boot-interview-questions/)
[JournalDev Spring-boot-interview-questions Wwhat-is-spring-boot](https://www.journaldev.com/8611/spring-boot-interview-questions#what-is-spring-boot)
[https://javarevisited.blogspot.com/2018/02/top-20-spring-rest-interview-questions-answers-java.html#axzz57Kv4wGXe](https://javarevisited.blogspot.com/2018/02/top-20-spring-rest-interview-questions-answers-java.html#axzz57Kv4wGXe)
[https://www.baeldung.com/spring-boot-interview-questions](https://www.baeldung.com/spring-boot-interview-questions)
[Edureka - microservices-interview-questions/](https://www.edureka.co/blog/interview-questions/microservices-interview-questions/)
