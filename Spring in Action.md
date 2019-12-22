![](https://lh4.googleusercontent.com/5-Npc0MGXAZWVk3TBrmBYrlT4aYR8qwiOHG7E4PMbBKfVLLEGv1WrKlv9MWh6T_u6O5pVBbf7HyY-_mJRWadndz7FLQMk0d5fqOgHvXpkIqUI-6pT_GQtj8XMEGtvrlOR1NAHVN8 "linha horizontal")

Spring in Action

15 de Junho de 2019

# OVERVIEW

Document referred to the book Spring Boot in Action teachings. Here I registered the most important aspects of the knowledge discussed by the book and references to be reviewed when necessary.

# MAIN POINTS

## Spring’s Annotations

@ModelAttribute(“”)/@ModelAttribute

The first annotate a parameter and the second annotates a method.

## Lombok Annotations

If the annotate some class with @Data and @NoArgsConstructor. The implicitly constructor added by @Data gets removed. So, to overcome this condition @RequiredArgsConstructor ensures that the constructor still remains been accessed.

## Processing Form Submissions - Without th:action=””

When the <form> doesn’t declare an action attribute. This means that when the form is submitted, the browser will gather up all the data in the form and send it to the server in an HTTP POST request to the same path for which a GET request displayed the form—the /design path.

# SPRING CONTENT

## Spring @SessionAttributes

## Spring @ModelAttribute - Dealing with Models

The @ModelAttribute annotation can be used on methods or on method arguments. Use @ModelAttribute (method) to load default data into your model before every request. The @ModelAttribute annotation can be used on @RequestMapping methods as well. In that case the return value of the @RequestMapping method is interpreted as a model attribute rather than as a view name.

@Controller

@RequestMapping("users")

public  class  UserController {

private  static  final  Logger LOGGER =  Logger.getLogger(UserController.class.getName());

private  static  int counter =  0;

  

@ModelAttribute("querier")

public  void  populateQuerierInfo (@RequestParam(value =  "querier",

required =  false)

String querier,

Model model) {

model.addAttribute("querier", querier ==  null  ?  "quest"  : querier);

}

}

  

Request: /users?querier=joe

[https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-model-attribute-method.html](https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-model-attribute-method.html)

  

Also, It's important to understand that all @ModelAttribute annotated methods are invoked on all requests. Spring doesn't provide a way to selectively invoke few @ModelAttribute methods based on the request. In our example, the last method populateQuerierInfo has @RequestParam with required=false. If we change it to required=true and the querier param is not present in the URL, then Spring will return this error:

  

## @ModelAttribute (parameter)

@PostMapping("/owners/{ownerId}/pets/{petId}/edit")

public String processSubmit(@ModelAttribute Pet pet) { }

  
  

Given the above example where can the Pet instance come from? There are several options:

-   It may already be in the model due to use of @SessionAttributes — see Section 16.3.3.9, “Using @SessionAttributes to store model attributes in the HTTP session between requests”.
    
-   It may already be in the model due to an @ModelAttribute method in the same controller — as explained in the previous section.
    
-   It may be retrieved based on a URI template variable and type converter (explained in more detail below).
    
-   It may be instantiated using its default constructor.
    

  
  

  

Use @ModelAttribute (parameter) if you want to use the result of @ModelAttribute (method) as a default:

## @ModelAttribute (parameter)

  

@ModelAttribute("attrib1")

SomeBean getSomeBean() {

return  new  SomeBean("neil");  // set modelMap["attrib1"] = SomeBean("neil") on every request

}

  

@RequestMapping("/a")

void pathA(@ModelAttribute("attrib1")  SomeBean someBean) {

assertEquals("neil", someBean.getName());

}

  

GET /a

  
  

  

Use @ModelAttribute (parameter) to get an object stored by @SessionAttributes:

## @ModelAttribute (parameter) - @SessionAttributes:

  

@Controller

@SessionAttributes("attrib1")

public  class  Controller1 {

  

@RequestMapping("/a")

void pathA(Model model) {

model.addAttribute("attrib1",  new  SomeBean("neil"));  //this ends up in session due to @SessionAttributes on controller

}

  

@RequestMapping("/b")

void pathB(@ModelAttribute("attrib1")  SomeBean someBean) {

assertEquals("neil", someBean.getName());

}

}

  

GET /a

GET /b

  
  
  

  

## Spring Data - Persisting Data

The Spring Data project is a rather large umbrella project comprised of several subprojects, most of which are focused on data persistence with a variety of different database types.

![](https://lh4.googleusercontent.com/txkbrFDK0vBhVPWuAXRUs8yeOHJJHbCNyd4kEKHZRGjcxM91WKxQE_4gus05yoAq0HniHHElGo62Z5XbU_TDBSbiVS1ydXnZ0V-aKhsc6johS5OcEnxAu_dyLHlEghVASL9A2DTm)

  

Entity - Spring Data JPA

To create an entity we must annotate a class with @Entity. And its id property must be annotated with @Id to designate it as the property that wiil uniquely identify the entity in database.

Lombok x JPA

JPA requires that entities have no-arguments constructor, so Lombok’s @NoArgsConstructor does that for us. The book recommends setup that annotation as private by setting the attribute to AccessLevel.PRIVATE with the force attribute as true. All those steps were performed, because in the class defined in the book. The variables were been defined as final.

Annotations

@Entity [class] -

@Table(name = ”MyDefinedTableName [class] - This specifies that entities of a specific class should be persisted to a table named MyDefinedTableName in the database.

@Id (variable) -

@GeneratedValue(strategy = GenerationType.AUTO) [variable] -

strategy

@PrePersist -

@ManyToMany(targetEntity = Kappa.class) [variable] -

Repository

-   JPARepository<T, ID>
    
-   Repository<T, ID>
    
-   CrudRepository<T, ID>
    

That Repository declares methods for CRUD (create, read, update, delete) operations. Notice that it’s parameterized, with the first parameter being the entity type and the second the ID type;

  

Customizing JPA Repositories

All the methods in the repository interface, parses the method name and attempts to ‘understand’ the method’s purpose in the context of the PERSISTED Object. In essence, Spring Data defines persistence details are expressed in repository method signatures.

Said that, repository methods are composed of a verb, an optional subject, the word by, and a predicate. Example:

findByDeliveryZip(String deliveryZip) → verb:find By predicate:DeliveryZip

  

![](https://lh4.googleusercontent.com/avL3Q5Vpj_6bJYExqof9eHNqvO9-bQHxN4vvf6OdwM3DnCryxbd0hwpMIzZO_4A9tKcmS5sqlrlMj3fmvIeU8RwvzbQ1fgldrI1caAVLPSf8iXNyhCoq4MqPVkYDuBSg5LM602-x)

  

  

### Spring Data Operator

-   IsAfter, After, IsGreaterThan, GreaterThan
    
-   IsGreaterThanEqual, GreaterThanEqual
    
-   IsBefore, Before, IsLessThan, LessThan
    
-   IsLessThanEqual, LessThanEqual
    
-   IsBetween, Between
    

-   readOrdersByDeliveryZipAndPriceAtBetween
    

-   IsNull, Null
    
-   IsNotNull, NotNull
    
-   IsIn, In
    
-   IsNotIn, NotIn
    
-   IsStartingWith, StartingWith, StartsWith
    
-   IsEndingWith, EndingWith, EndsWith
    
-   IsContaining, Containing, Contains
    
-   IsLike, Like
    
-   IsNotLike, NotLike
    
-   IsTrue, True
    
-   IsFalse, False
    
-   Is, Equals
    
-   IsNot, Not
    
-   IgnoringCase, IgnoresCase
    

  

1.  AllIgnoresCase
    

List<Order> findByDeliveryToAndDeliveryCityAllIgnoresCase(String deliveryTo, String deliveryCity);

2.  OrderBy
    

List<Order> findByDeliveryToAndDeliveryCityAllIgnoresCase(String deliveryTo, String deliveryCity);

  

  

### @Query - Customizing JPA Repositories

@Query annotation explicites specify the query to be performed when the method is called, as this example shows

  

## Spring MVC Validation - Input form

To ensure the consistency of the input from forms Spring provides support for Java’s Beans validation. To apply validation is only necessary to

-   Declare validation rules on the class that is to be validated.
    
-   Specify that the validation should be performed in the controller methods (insert validation annotations to indicate this statement) .
    
-   Insert logic in the views to display the validation errors
    

Method’s Annotations - javax.validation.constraints

@NotNull(message = “”)

@Size(message = “”)

@NotEmpty(message = “”)

@Max/Min(message = “”)

@Positive/@Negative(message = “”)

@Pattern(regex, message = “”)

  

Example

public class Food {

  

@NotNull //

@Size(min = 5, message = "Name must be at least 5 characters long")

private String name;

  

@NotNull

@Size(min = 1, message = "Choose at least one ingredient")

private List<String> ingredients;

}

  

  

### Parameter Annotations - javax.validation.constraints

The @Valid annotation tells Spring MVC to perform validation on the submitted object after it’s bound to the submitted form data and before the method processDesign() method is called. If there are any validation errors, the details of those errors will be captured in an Errors object that’s passed into processDesign(). The first few lines of the method processDesign() consult the Errors object, asking its hasErrors() method if there are any validation errors. If there are, the method concludes without processing the object passed and returns the "design" view name so that the form is redisplayed.

  

## Example

@PostMapping

public String processDesign(@Valid @ModelAttribute("design") Food design, Errors errors, Model model) {

//TODO verify how does VALIDATION is performed in spring

if(errors.hasErrors()) {

return "design";

}

  

Displaying Validation Errors

To access to the Errors object from the validation Thymeleaf offers the property fields and with its th:errors attribute.

  

## Example - View logic

<label for="ccNumber">Credit Card #: </label>

<input type="text" th:field="*{ccNumber}"/>

<span class="validationError"

th:if="${#fields.hasErrors('ccNumber')}"

th:errors="*{ccNumber}">CC Num Error</span>

  

The if attribute will decide whether or not to display de <span>. The hasErrors method checks if there are errors in the ‘ccNumber’ field. If so, the <span> will be rendered;

Processing Form Submissions - Without th:action=””

When the <form> doesn’t declare an action attribute. This means that when the form is submitted, the browser will gather up all the data in the form and send it to the server in an HTTP POST request to the same path for which a GET request displayed the form—the /design path.

## A New Way to Declare View Controller - @WebConfig

@WebConfig implements the WebMvcConfigurer interface. The last one define several methods for configuring Spring MVC. Also, it provide default implementation of all methods, so that is only needed to override the methods.

The method addViewControllers

The addViewControllers method receives as parameter a ViewControllrolerRegistry that can be used to register one or more view controllers by the addViewController(“/”) (it is not the addViewControllers). The value inside the method is the path which the view controller will handle GET requests. Another method is setViewName() that specify the view that a request for the (“/”) should be forwarded to.

  

## Example - View WebConfig

@Configuration

public class WebConfig implements WebMvcConfigurer {

//TODO See WebMvcConfigurer methods

  

@Override

public void addViewControllers(ViewControllerRegistry registry) {

registry.addViewController("/").setViewName("home");

//addViewController sets the path and ne view name of the defined path

}

}

  

## Digite seu texto aqui

Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui.

  

# OBJETIVOS

1.  Digite seu texto aqui Digite seu texto aqui.
    
2.  Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui Digite seu texto aqui.
    

# LOGGING IN SPRING APPLICATION

[https://bit.ly/31EICFU](https://bit.ly/31EICFU)

[https://www.javadevjournal.com/spring-boot/logging-application-properties/](https://www.javadevjournal.com/spring-boot/logging-application-properties/)

[https://www.baeldung.com/logback](https://www.baeldung.com/logback)

# CONFIGURATION PROPERTIES - CUSTOM

In Spring is possible to set up custom properties that will be consumed by the beans. To do that, spring provides the annotation @ConfigurationProperties. When placed on any Spring bean, it specifies that the properties of that bean can be injected from properties in the Spring environment.

  

  

@Controller

@RequestMapping("/orders")

@SessionAttributes("order")

@ConfigurationProperties(prefix="taco.orders")

public  class  OrderController {

private  int pageSize =  20;

public  void setPageSize(int pageSize) {

t his.pageSize = pageSize;

}

...

@GetMapping

public  String ordersForUser(

@AuthenticationPrincipal  User user,  Model model) {

Pageable pageable =  PageRequest.of(0, pageSize);

model.addAttribute("orders",

orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

return  "orderList";

}

}

  

#In the application.yml property file

...

taco:

orders:

pageSize: 10

...

  

  
  

# SETUP ENVIRONMENT TO HANDLE HTTPS REQUESTS

The first step is to create a keystore using the JDK’s keytool command-line utility:

$ keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA

PS: You’ll be asked several questions about your name and organization, most of which are irrelevant. But when asked for a password, remember what you choose. For the sake of this example, I chose letmein as the password.

The next step requests setup a few properties to enable HTTPS in the embedded server. Is possible specify them all on the command line, but that would be terribly inconvenient. Instead, set them in the file’s application.properties or application.yml. In application.yml, the properties might look like this:

server:

port: 8443

ssl:

key-store: file:///path/to/mykeys.jks

key-store-password: letmein

key-password: letmein

Here the server.port property is set to 8443, a common choice for development HTTPS servers. The server.ssl.key-store property should be set to the path where the keystore file is created. Here it’s shown with a file:// URL to load it from the filesystem, but if you package it within the application JAR file, you’ll use a classpath: URL to reference it. And both the server.ssl.key-store-password and server .ssl.key-password properties are set to the password that was given when creating the keystore .
