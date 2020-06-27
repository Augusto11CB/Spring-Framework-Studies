# Beans and IoC

## What Is a Spring Bean?
The Spring Beans are Java Objects that are initialized by the Spring IoC container.

## Source of Beans Definitions - @Configuration

Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions. The Spring IoC container manages objects called **beans**, which is instantiated, assembled and managed by Spring IoC.

IoC (Inversion of Control) is a process in which an object defines its dependencies without creating them. That object **delegates** the job of constructing such dependencies to an IoC container.

## IoC

Spring IoC stands for Inversion of Control.

The important tasks performed by the IoC container are:

1.  Instantiating the bean
2.  Wiring the beans together
3.  Configuring the beans
4.  Managing the bean’s entire life-cycle

There are two types of IoC containers:

1.  BeanFactory
2.  ApplicationContext

* **ApplicationContext - Example**
```java
	@Component
    public static class MyClass {

        ApplicationContext context;

        public MyClass(ApplicationContext context) {
            this.context = context;
        }

        @PostConstruct
        public void postConstruct() {
            for (SomeEnum someEnum : EnumSet.allOf(SomeEnum.class)) {
                NiceStatus niceStatus = context.getBean(someEnum.niceStatus);
                
            }
        }
    }
```






## The @PostConstruct Annotation
Javax's _@PostConstruct_ annotation can be used for annotating a method that should be run **once immediately after the bean's initialization**. Keep in mind that the annotated method will be executed by Spring even if there is nothing to inject.

```java
@Component
public class PostConstructExampleBean {
 
    private static final Logger LOG 
      = Logger.getLogger(PostConstructExampleBean.class);
 
    @Autowired
    private Environment environment;
 
    @PostConstruct
    public void init() {
        LOG.info(Arrays.asList(environment.getDefaultProfiles()));
    }
}
```
In the example above  the _Environment_ instance was safely injected and then called in the _@PostConstruct_ annotated method without throwing a _NullPointerException_.

## Using Beans - Approaches



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
