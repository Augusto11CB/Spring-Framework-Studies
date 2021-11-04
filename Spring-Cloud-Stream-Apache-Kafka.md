# Spring Cloud Stream + Apache Kafka

https://benwilcock.github.io/spring-cloud-stream-demo/
https://blog.avenuecode.com/processing-messages-with-spring-cloud-stream-and-kafka
https://spring.io/blog/2020/12/15/testing-spring-cloud-stream-applications-part-1

- A binding is a bridge between the application and the external messaging systems (Kafka, RabbitMQ, etc). The binding sends and receives messages.

- A binding is abstract, and the application should not know or care if it's a Kafka or a RabbitMQ implementation.

- In a data pipeline, a stream of data originates from a Source and flows into a Sink, with zero or more processing steps in between. In practice, the Source acts as a Supplier of data from some external resource, such as a data store, any service supporting a standard protocol, or a message broker. The Sink acts as a consumer of data for some other external resource. 

```
@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public Function<String, String> uppercase() {
	    return value -> {
	        System.out.println("Received: " + value);
	        return value.toUpperCase()
	    };
	}
}
```

- The above code receives the payload of the message as a String type, logs it to the console and sends it down stream after converting it to upper case.


> We could use the spring.cloud.function.definition property in the application.properties file to explicitly declare which function bean we want to be bound to binding destinations - but for cases when you only have single @Bean defined, this is not necessary.


- Unlike previous versions of spring-cloud-stream which relied on `@EnableBinding` and `@StreamListener` annotations, the above example looks no different then any vanilla spring-boot application. It defines a single bean of type `Function`

    - **how does it became spring-cloud-stream application?**
        - it becomes spring-cloud-stream application simply based on the presence of spring-cloud-stream and binder dependencies and auto-configuration classes on the classpath effectively setting the context for your boot application as spring-cloud-stream application.


- Binding and Binding names

    - Functional binding names
        - In the preceding example the code has a single function which acts as message handler. As a `Function` it has an input and output. The naming convention used to name input and output bindings is as follows:

            - input - `<functionName> + -in- + <index>`

            - output - `<functionName> + -out- + <index>`

        - The index is the index of the input or output binding. It is always 0 for typical single input/output function.

        - Ex: map the input of this function to a remote destination (e.g., topic, queue etc) called "my-topic" 
            - `--spring.cloud.stream.bindings.uppercase-in-0.destination=my-topic`

    - Descriptive Binding Names
        - It is possible to map an implicit binding name to an explicit binding name.
        - It is done by using `spring.cloud.stream.function.bindings.<binding-name>` property
        - Example:
            - ` --spring.cloud.stream.function.bindings.uppercase-in-0=input`
            - Now mapped and effectively renamed `uppercase-in-0` binding name to `input`. Now all configuration properties can refer to input binding name instead (e.g., --spring.cloud.stream.bindings.input.destination=my-topic).


