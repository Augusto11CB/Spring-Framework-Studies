
# Exception Handling in Spring - Best Practices

## Summary
* Contextualisation and Problematic Case
* Exception Handling via @ResponseStatus
* Exception Handling via @RestControllerAdvice

## Contextualization and Problematic Case

Suppose an **user service** where is possible fetch and update registers. Following these structure:

**Entity**
```java
public  class User { 
	private  int id; 
	private String name; 
	private  int age;
}
```
**Controller**
```java
@GetMapping(value = "/user/{id}")  
public ResponseEntity<?> getUser(@PathVariable int id) {  
    if (id < 0) {  
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  
  }  
    User user = findUser(id);  
 if (user == null) {  
	 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  
  }    
     return ResponseEntity.ok(user);  
}
```
In the above Controller code, there is a validation for id and another validation for found out user. For each check, a **ResponseEntity** is created having reponse codes according with the de validation.

As far as the logic in the controller increases, is necessary create those **ResponseEntities** for each case. That situation compromises the maintenance and the readability.

The right approach to follow towards is instead of handling validation/error scenarios and what responses have to be returned, the alternative solution intend to manipulate all theses contexts by using throwable exceptions after a violation. These exception will be then handled separately.

##  Exception Handling via @ResponseStatus
The annotation @ResponseStatus can be used on methods and **exceptions classes** in order to apply a HttpStatus when some kind of Exception happens.

To do that, first declare a custom exception and annotes it with @RespondeStatus

**Custom Exception**
```java
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not found") 
public  class UserNotFoundException extends RuntimeException{ 
}
```
When Spring catches this Exception, it uses the configuration provided in `@ResponseStatus`.

**New Controller**
```java
@GetMapping(value = "/user/{id}")
public ResponseEntity<?> getUser(@PathVariable int id) {
    if (id < 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //CHANGE CHANGE CHANGE
    User user = findUser(id);
    return ResponseEntity.ok(user);
}
```
Now  the value of the ResponseEntity is no longer set in the controller. If no user were found what is going to happen is the throwing of exception.

## @RestControllerAdvice and @ExceptionHandler

Defining exceptions handlers that will be activeted when mapped exceptions were thrown. Inside these handlers could be written special logic according to business needs.

**Exemplification Case - Update User Age**
Conditions: the **age** will be only updated if it is within the range of 01 to 150 and if the **id** is greater than 0.

```Java
@PatchMapping(value = "/user/{id}")
public ResponseEntity<?> updateAge(@PathVariable int id, @RequestParam int age) {
    if (id < 0) {
        throw new ValidationException("Id cannot be less than 0");
    }
    if (age < 1 || age > 150) {
        throw new ValidationException("Age must be between 01 to 60");
    }
    User user = findUser(id);
    user.setAge(age);

    return ResponseEntity.accepted().body(user);
}
```

By default `@RestControllerAdvice` is applicable to the whole application but you can restrict it to a specific package, class or annotation.

``@RestControllerAdvice(basePackages = "my.package")``

-	To apply to specific class:

		``@RestControllerAdvice(<assignableTypes> <basePackageClasses> = MyController.class)``

-	Defining to a certain controller
			
		``@RestControllerAdvice(annotations = RestController.class)``

###  Implementing the ExceptionHandler

``` Java
@RestControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Required request params missing");
    }

    private ResponseEntity<Object> errorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}
```

## Using MessageSourcer to Standardize error Message

```
// private method of ExceptionHandler to get the messagens defined in the messages.properties file.

private fun getMessageInProperties(ex: Exception) {
// the Exception's message field has the code message registred in messages.properties
  messageSource.getMessage(ex.message!!, null, ex.message ?:  "server.error", Locale.getDefault())

}

```
messageSource.getMessage(ex.message!!, null, ex.message ?: "server.error", Locale.getDefault())

* File **messages.properties**:


## References
[Stack Abuse - Exception Handling in Spring ](https://stackabuse.com/exception-handling-in-spring/)
