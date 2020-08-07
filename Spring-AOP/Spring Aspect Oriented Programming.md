
# Spring Aspect Oriented Programming

## What Is Aspect Oriented Programming?
AOP comes to solve a problem with code that is called **code of crosscutting functionallity**, such as logging, validation, user rights checks, caching, exception handling, etc. By moving it from the business code logic in order to avoid the latter being mixed with the others.

The main ideia is to remove and create specialized classes for those crosscutting functionallity, which also can be called **aspects (modularization of a concern)**

1. Do something before the method
2. Call the method
3. Do something after the method

### AOP Key-words
- Aspect: A modularization of a concern that cuts across multiple classes. Transaction management is a good example of a crosscutting concern in enterprise Java applications. In Spring AOP, aspects are implemented by using regular classes (the schema-based approach) or regular classes annotated with the `@Aspect` annotation (the @AspectJ style).

- Join point: A point during the execution of a program, such as the **execution of a method** or the **handling of an exception**. In Spring AOP, a join point always represents a method execution.

- Advice: Action taken by an aspect at a particular join point. Different types of advice include “around”, “before” and “after” advice. Many AOP frameworks, including Spring, model an advice as an interceptor and maintain a chain of interceptors around the join point.

- Pointcut: A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut (for example, the execution of a method with a certain name).











### Project - Manager of Flights and Passengers
