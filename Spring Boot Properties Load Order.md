# Spring Boot Properties Load Order

[](https://stackoverflow.com/posts/25862357/timeline)

When using Spring Boot the properties are loaded in the following order (see  [Externalized Configuration](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)  in the Spring Boot reference guide).

1.  Command line arguments.
2.  Java System properties (System.getProperties()).
3.  OS environment variables.
4.  JNDI attributes from java:comp/env
5.  A RandomValuePropertySource that only has properties in random.*.
6.  Application properties outside of your packaged jar (application.properties including YAML and profile variants).
7.  Application properties packaged inside your jar (application.properties including YAML and profile variants).
8.  @PropertySource annotations on your @Configuration classes.
9.  Default properties (specified using SpringApplication.setDefaultProperties).

 
