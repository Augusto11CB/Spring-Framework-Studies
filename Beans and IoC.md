# Beans and IoC

## Source of Beans Definitions - @Configuration

Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions. The Spring IoC container manages objects called **beans**, which is instantiated, assembled and managed by Spring IoC.

IoC (Inversion of Control) is a process in which an object defines its dependencies without creating them. That object **delegates** the job of constructing such dependencies to an IoC container.

## IoC

TODO 

## Using Beans - Approaches

<![endif]-->

As we have discussed, when a bean is declared inside a config class it is going to be available for the entire application context thanks spring and its code inspection.  
  
To use a instance of an object declared as a bean we can use either the construction inject or @Autowired.

If a config class has two beans with the same return type, aim avoiding the follow exception expected single matching bean but found

There are two approaches:  
  
1 - Constructor Dependency Injection Injections The object passed as a parameter must have the same name as the method name within the configuration class.
``` java
@Configuration
class config {

	@Bean
	Fun myBean():MyBean{}

	@Bean(name = "myAnotherBean")
	Fun myAnotherBean():MyBean{}

}

@Component
class SomeClass {
	@Bean  
	public MyBean myOtherBeanWithInjection(
	@Qualifier("myAnotherBean") MyBean myBean
	) { 
		return  new MyBeanWithInjectionImpl(myBean); 
	}
}
```
2 - With autowired we can use the annotation @Qualifier in the config class and the name defined inside by the qualifier must be used as the name of the variable autowired.

TODO - Read [Article about bean configuration](https://www.codingame.com/playgrounds/2096/playing-around-with-spring-bean-configuration)

## Beans Scope
[Spring Bean Scopes](https://www.baeldung.com/spring-bean-scopes)

## References

[https://www.codingame.com/playgrounds/2096/playing-around-with-spring-bean-configuration](https://www.codingame.com/playgrounds/2096/playing-around-with-spring-bean-configuration)

[https://stackoverflow.com/questions/53130086/confusion-about-the-spring-beans-with-the-same-type-and-different-parameter-as-t](https://stackoverflow.com/questions/53130086/confusion-about-the-spring-beans-with-the-same-type-and-different-parameter-as-t)
