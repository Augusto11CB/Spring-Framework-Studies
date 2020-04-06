---


---

<h2 id="spring-vs-spring-boot">Spring Vs Spring Boot</h2>
<ul>
<li><strong>Spring Framework</strong> aims to simplify Java Applications Development.</li>
<li><strong>Spring Boot Framework</strong> aims to simplify Spring Development.</li>
</ul>
<h2 id="what-is-spring-boot-and-its-purposes">What is Spring Boot and Its Purposes</h2>
<p><strong>Spring Boot Framework</strong> is:<br>
Auto-Dependency Resolution (Spring Boot Starter),<br>
Auto-Configuration (Spring Boot Auto-Configurator),<br>
Management EndPoints (Spring Boot Actuator),<br>
Embedded HTTP Servers(Jetty/Tomcat etc.),<br>
Spring Boot CLI</p>
<h2 id="advantages-of-spring-boot">Advantages of Spring Boot</h2>
<ul>
<li>auto-configuration -</li>
<li>Spring boot follows “Opinionated Defaults Configuration” Approach, which means that it helps you to setup a fully working application(web app or otherwise) very quickly by providing you intelligent default configurations that you are most likely to be satisfied to start with. Also, iit can be overrided in order to atend the needs of some requeriment.</li>
</ul>
<h2 id="spring-boot-and-transitively-dependency-resolution-management-by-gradlemaven">Spring Boot and Transitively Dependency Resolution Management by Gradle/Maven</h2>
<p><strong>What is “Transitively Dependency Resolution Management”?</strong>  These are dependencies that are dependencies of your direct dependencies.</p>
<h2 id="spring-boot-starter-dependency">Spring Boot Starter Dependency</h2>
<p>Spring Boot starters are a set of dependency management providers. Spring Boot aggregates common dependencies together. All the available starters come under the org.springframework.boot group.</p>
<h2 id="what-is-auto-configuration-in-spring-boot-and-how-to-disable-the--auto-configuration">What is auto-configuration in Spring Boot and How to disable the  auto-configuration?</h2>
<h2 id="explain-spring-actuator">Explain Spring Actuator</h2>
<h2 id="explain-spring-data">Explain Spring Data</h2>
<h2 id="can-we-create-a-non-web-application-in-spring-boot">Can we create a non-web application in Spring Boot?</h2>
<p>Yes, we can create a non-web application by removing the web dependencies from the classpath along with changing the way Spring Boot creates the application context.</p>
<h2 id="how-is-hibernate-chosen-as-the-default-implementation-for-jpa-without-any-configuration">How is Hibernate chosen as the default implementation for JPA without any configuration?</h2>
<p>When we use the <strong>Spring Boot Auto Configuration,</strong> automatically the <strong>spring-boot-starter-data-jpa</strong> dependency gets added to the pom.xml file. Now, since this dependency has a transitive dependency on JPA and Hibernate, Spring Boot automatically auto-configures Hibernate as the default implementation for JPA</p>
<h2 id="what-is-spring-data-rest">What is Spring Data REST?</h2>
<h3 id="how-does-path”sample”-collectionresourcerel”sample”-work-with-spring-data-rest">How does path=”sample”, collectionResourceRel=”sample” work with Spring Data Rest</h3>
<h2 id="in-which-layer-should-the-boundary-of-a-transaction-start">In which layer, should the boundary of a transaction start?</h2>
<p>The boundary of the transaction should start from the Service Layer since the logic for the business transaction is present in this layer itself.</p>
<h2 id="why-is-spring-data-rest-not-recommended-in-real-world-applications">Why is Spring Data REST not recommended in real-world applications?</h2>
<p>Spring Data REST is not recommended in real-world applications as you are exposing your database entities directly as REST Services.</p>
<h2 id="what-does-rest-stand-for">What does REST stand for?</h2>
<h2 id="what-is-a-resource">What is a Resource?</h2>
<h2 id="what-are-idempotent-operations-why-is-idempotency-important">What are idempotent operations? Why is idempotency important?</h2>
<p>// TODO <a href="https://javarevisited.blogspot.com/2016/05/what-are-idempotent-and-safe-methods-of-HTTP-and-REST.html">https://javarevisited.blogspot.com/2016/05/what-are-idempotent-and-safe-methods-of-HTTP-and-REST.html</a></p>
<h2 id="what-is-spring-cloud">What is Spring Cloud?</h2>
<p>According to the official website of Spring Cloud, Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, leadership election, distributed sessions, cluster state)</p>
<h3 id="problems-solved-by-spring-cloud">Problems solved by Spring Cloud</h3>
<p>The issues which are solved by Spring Cloud are:</p>
<ul>
<li><strong>The complexity associated with distributed systems –</strong> This includes network issues, Latency overhead, Bandwidth issues, security issues.</li>
<li><strong>Ability to handle Service Discovery –</strong> Service discovery allows processes and services in a cluster to find each other and communicate.</li>
<li><strong>Solved redundancy issues –</strong> Redundancy issues often occur in distributed systems.</li>
<li><strong>Load balancing</strong> <strong>–</strong> Improves the distribution of workloads across multiple computing resources, such as a computer cluster, network links, central processing units.</li>
<li><strong>Reduces performance issues –</strong> Reduces performance issues due to various operational overheads.</li>
</ul>
<h2 id="references">References</h2>
<p><a href="https://www.edureka.co/blog/interview-questions/spring-boot-interview-questions/">Edureka Spring Boot Interview Questions</a><br>
<a href="https://www.journaldev.com/8611/spring-boot-interview-questions#what-is-spring-boot">JournalDev Spring-boot-interview-questions Wwhat-is-spring-boot</a><br>
<a href="https://javarevisited.blogspot.com/2018/02/top-20-spring-rest-interview-questions-answers-java.html#axzz57Kv4wGXe">https://javarevisited.blogspot.com/2018/02/top-20-spring-rest-interview-questions-answers-java.html#axzz57Kv4wGXe</a><br>
<a href="https://www.baeldung.com/spring-boot-interview-questions">https://www.baeldung.com/spring-boot-interview-questions</a><br>
<a href="https://www.edureka.co/blog/interview-questions/microservices-interview-questions/">Edureka - microservices-interview-questions/</a></p>

