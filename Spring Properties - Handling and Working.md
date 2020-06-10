
# @ConfigurationProperties: Binding external configurations to classes


## References
[https://www.callicoder.com/spring-boot-configuration-properties-example/](https://www.callicoder.com/spring-boot-configuration-properties-example/)


## Access properties file programmatically with Spring
```kotlin
val resource: Resource = ClassPathResource("/messages.properties")  
val props: Properties = PropertiesLoaderUtils.loadProperties(resource)
return props.getProperty(propName)
```
