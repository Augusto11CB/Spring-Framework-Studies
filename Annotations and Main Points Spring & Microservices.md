Annotations and Main Points  
Spring & Microservices

![](https://lh3.googleusercontent.com/3H5JoH2ZI-IC_oFiqk0SU06UK-Pim31V-XkFNHJkOmg1AznS9EFqbjtyfVs2oIYj63v_I0S7vAAtVxMzd1PKxrjvoqpGkMufWx2eoJ1tIzkbtgt5N3e0wfJbUdigYX9OrPnylNKT "linha curta")

Augusto Calado Bueno  
01 September - 2019

  

# Interesting Information

## Overload Controller Method in Java Spring

[https://stackoverflow.com/questions/30380498/overload-controller-method-in-java-spring](https://stackoverflow.com/questions/30380498/overload-controller-method-in-java-spring)

## Jackson-Annotations

[https://www.baeldung.com/jackson-annotations](https://www.baeldung.com/jackson-annotations)

## Cross-Origin Resource Sharing (CORS) - @CrossOrigin

For security reasons, browsers prohibit AJAX calls to resources residing outside the current origin. When working with client-side HTTP requests issued by a browser, you want to enable specific HTTP resources to be accessible.

  

Swagger - Dependencies

@CrossOrigin(origins =  "http://domain2.example",

methods =  {  RequestMethod.GET,  RequestMethod.POST,  RequestMethod.DELETE },

maxAge =  3600)

interface  PersonRepository  extends  CrudRepository<Person,  Long>  {}

  

# Configuration Properties - @ConfigurationProperties

With Spring is possible to set up custom properties that will be consumed by the beans. In order to do that, Spring provides the annotation @ConfigurationProperties. When it is placed on any Spring’s bean, it specifies which properties (these, in turn, are set in the file application.propertie), is going to be used by the bean annotated with @ConfigurationProperties.

In the following example, there is a property called “taco.orders.pageSize” in application.properties. @ConfigurationProperties receives parameters determining the property prefix. In our case is “taco.order”. The entry used by or code snippet is pageSize.

  

Swagger - Dependencies

  

@Controller

@RequestMapping("/orders")

@SessionAttributes("order")

@ConfigurationProperties(prefix="taco.orders")

public  class  OrderController {

private  int pageSize =  20;

public  void setPageSize(int  pageSize) {

this.pageSize = pageSize;

}

...

@GetMapping

public  String ordersForUser(

@AuthenticationPrincipal  User user,  Model model) {

Pageable pageable =  PageRequest.of(0,  pageSize);

model.addAttribute("orders",

orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

return  "orderList";

}

}

  

  

# Swagger

Making API’s documentation using Swagger which is a set of open-source tools built around the OpenAPI Specification that can help design, build, document and consume REST APIs.

<dependency>

<groupId>io.springfox</groupId>

<artifactId>springfox-swagger2</artifactId>

<version>2.8.0</version>

</dependency>

  

<dependency>

<groupId>io.springfox</groupId>

<artifactId>springfox-swagger2</artifactId>

<version>2.8.0</version>

</dependency>

## Setting up The Configuration Class

To enable toe Swagger is necessary use the annotation @EnableSwaggwer2. in a standard spring configuration class (annotated with @Configuration). Also, a Docket needs to be created. That provides the primary API configuration.

To create Docket some parameters such as apis() and paths() are passing. Methods that are used to filter the controllers and methods that are being documented using String predicates.

  

Swagger - ConfigClass

@Configuration

@EnableSwagger2

public  class  Swagger2Config {

@Bean

public  Docket api() {

return  new  Docket(DocumentationType.SWAGGER_2).select()

.apis(RequestHandlerSelectors

.basePackage("net.guides.springboot2.springboot2swagger2.controller"))

.paths(PathSelectors.regex("/api/*"))

.build().apiInfo(apiEndPointsInfo());

}

private  ApiInfo apiEndPointsInfo() {

return  new  ApiInfoBuilder().title("Spring Boot REST API")

.description("Employee Management REST API")

.contact(new  Contact("Ramesh Fadatare",  "www.javaguides.net",  "ramesh24fadatare@gmail.com"))

.license("Apache 2.0")

.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")

.version("1.0.0")

.build();

}

}

Above, using RequestHandlerSelectors is possible to filter which endpoints is going to be considered based on their package. To list only the endpoints of our application use the methods basePackage(“com.aug.controller”).

Additionally, PathSelectors provides us ways to filter the paths allowed for the endpoints. In the example was specified that that only path starting with /API should be picked up.

springfox.documentation.swagger.v2.path

## Custom Documentation Information

## Swagger 2 Annotations for REST Endpoints

## Custom Methods Response Messages

## Swagger With Security Configuration

  

[https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg](https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg)

[https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

[https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/](https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger/)

[https://medium.com/@raphaelbluteau/spring-boot-swagger-documentando-sua-api-automaticamente-27903293aeb6](https://medium.com/@raphaelbluteau/spring-boot-swagger-documentando-sua-api-automaticamente-27903293aeb6)

  

# Spring-HATEOAS - REST Services

Spring HATEOAS allows us, while we are building our APIs, follow the HATEOAS principles, which is: “the API should guide the client through the application by returning relevant information about the next potential steps, along with each response.”

With HATEOAS the resources returned from an API contain links to other related resources. This enables clients to navigate an API with minimal understanding of the URLs.

HAL (Hypertext Application Language -> simple and commonly used format for embedding hyperlinks in JSON responses.

Spring HATEOAS provides two primary types that represent hyperlinked resources: Resource and Resources. The first one represents a single resource whereas the second if a collection of resources.

## ControllerLinkBuilder - Avoid Hardcoding URLs

To avoid hardcoding URL Spring HATEOAS provides a link builder called ControllerLinkBuilder

Ex.: ControllerLinkBuilder.linkTo(methodOn(Class.class).myMethodRandom())withRel(“name of the relation”));

Above, ControllerLinkBuilder uses the Class.class base path as the foundation of the Link object we are creating. The methodOn takes some class and allows us to make calls to the methods defined inside the class passed as a parameter of methodOn. In the example, the call the class called myMethodRandom().

[https://stackoverflow.com/questions/25651820/generate-links-with-additional-query-parameters-using-pagedresourcesassembler](https://stackoverflow.com/questions/25651820/generate-links-with-additional-query-parameters-using-pagedresourcesassembler)

[This link contains information Generate links with additional query parameters using]

## Creating Resource Assemblers

We create those new EntityResource because of the creation of the links among the objects that are going to be returned from the API.

  

Swagger - Dependencies

EntityResource  extends  ResourceSupport {

#Test Constructor in kotlin

# [Java] The constructor will receive the X entity and copies its values to the variables in XResource

}

## Converting Normal Entities to XEntitiesResource

To aid in converting Entity to EntityResource is necessary to create a resource assembler.

  

Swagger - Dependencies

EntityResourceAssembles  extends  ResourceAssemblerSupport  <Entity,  EntityResource>

#Constructor that involks 'super'

# instantiateResource(Entity en)

# toResource(Entity en)

}

The default constructor of EntityResourceAssembler informs its superclass that it will be using some class to determine the base path for any URL in links it creates when creating an EntityResource.

-  instantiateResource() instantiate an EntityResource given an Entity. Is optional override this method when the EntityResource that we want to great has a default constructor.

-  Mandatory when extends ResourceAssemblerSupport. What this method does is create an EntityResource from an Entity and gives it a self-link. Under the cover, this method will call instantiateResource() to create the EntityReosurce.

## Naming Embedded Relationships

With the @Relation annotation, we can specify how spring Hateos should name the field in the resulting JSON.

@Relation(value = “name of single unity in json”, collectionRelation=”name of a collection of unities in json”)

Class EntityResource (): ResourceSupport

  

[Create a ResourceSupport in kotlin]

[https://ivanqueiroz.dev/2019/04/java-kotlin-spring-boot.html](https://ivanqueiroz.dev/2019/04/java-kotlin-spring-boot.html)

[https://gist.github.com/romixch/1c4a5f441951045acf6d22c8e3af483e](https://gist.github.com/romixch/1c4a5f441951045acf6d22c8e3af483e)

  

[understand the linkTo - analysis the connection that we can do in our own link]

[https://lankydan.dev/2017/09/10/applying-hateoas-to-a-rest-api-with-spring-boot](https://lankydan.dev/2017/09/10/applying-hateoas-to-a-rest-api-with-spring-boot)

  

[Resource assembler]

[https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.resource-assembler](https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.resource-assembler)

[https://www.logicbig.com/tutorials/spring-framework/spring-hateoas/resource-assembler.html](https://www.logicbig.com/tutorials/spring-framework/spring-hateoas/resource-assembler.html)

# Spring Data REST

Link [https://docs.spring.io/spring-data/rest/docs/current/reference/html/#preface](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#preface)

Spring Data REST is a member of Spring Data family that automatically creates REST APIs for repositories created by Spring Data.

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-data-rest</artifactId>

</dependency>

By adding the Dependency the project enables automatic creation of REST API for any repository inside the project.

## Adjusting Base Path

To set the base path of the endpoints created by Spring Data REST add the following property inside applications.properties

spring.data.rest.base-path = /api

## @RepositoryRestResource

@RepositoryRestResource is used to set options on the public Repository interface - it will automatically create endpoints as appropriate based on the type of Repository that is being extended

Spring Data REST exposes a collection resource named after the uncapitalized, pluralized version of the domain class (in other words the entity). Both the name of the resource and the path can be customized by using @RepositoryRestResource(collectionResourceRel = "people", path = "people").

Often some resources, methods should not be exported at all. To tell the Spring Data REST exporter to not export these items, annotate them with set the parameter exported = false.

Ex.: @RepositoryRestResource(collectionResourceRel = "people", path = "people", exported = false).

## @RestResource - Adjusting Resource Paths & Relation Names

### @RestResource(exported = false)

## @RepositoryRestController

Even though the facilities provided by Spring Data REST, sometimes is necessary to create a custom handler for a specific resource. So that we can use the annotation @RepositoryRestController in our controllers. By doing that, the base path of this newest handler is going to be shared by the default base path defined for the REST operations of Spring Data Rest (spring.data.rest.base-path property)

@RepositoryRestController is an annotation for annotating controller classes whose mappings should assume a base path that is the same as the one configured for Spring Data REST. It can be used when there is a necessity to create an endpoint which cannot be solved by the default features of the Spring Data Rest and/or needs to implement a certain logic.

## Paging and Sorting

### PAGING

Spring Data REST also provides Paging and Sorting. To make use of those features in the Rest endpoint is possible to define the size and page of the parameter.

Ex.: localhost:8080/api/ingredients?size=5&page=1

Notice that the page parameter is zero-based, which means that asking for page number 1 is actually asking for the second page.

The returns of Spring Data Rest can be really helpful since they have links in HEATEOAS format. There are links for FIRST, LAST, NEXT and Previous pages in the response.

### SORTING

The sort parameters allow sorting the result by any property of the entity.

Ex.: localhost:8080/api/ingredients?sort=price,desc&size=5&page=1

#PS: the price is an entity attribute

![](https://lh5.googleusercontent.com/k_YnwJg8IvxUParWbXR6a80LjpB3VIakvVsQ9gm9mdRM3bIUd-AhA-zRa4n0MQ2haNR9PQou2sOYHbh93BYb8fXEfyleTGV-_tBiD6AD0O3K5LJNpt_PxNzr-KNBkVcqOolQipJd)

## Projections and Excerpts

First consider an EntitySuper composed by its field plus EntityA. If an EntityA domain object does not have its own repository definition, Spring Data REST includes the data fields inside the EntitySuper, as the following example shows:

[https://docs.spring.io/spring-data/rest/docs/current/reference/html/#projections-excerpts.projections](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#projections-excerpts.projections)

## Adding Custom Hyperlinks - Spring Data Endpoints

If controller classes were created with the annotation @RepositoryRestController, their hyperlink won’t be returned among the other automatic generated links. To add those new controllers their hyperlinks the interface ResourceProcessor of Spring Data HATEOAS is can be used.

Spring HATEOAS defines a ResourceProcessor<> interface for processing entities. All beans of type ResourceProcessor<Resource<T>> are automatically picked up by the Spring Data REST exporter and triggered when serializing an entity of type T.

For your purposes, you need an implementation of ResourceProcessor that adds a recent link to any resource of type PagedResources<Resource<XEntity>> (the type returned for the /api/XEntity endpoint).

  

Spring DATA Rest & HATEOAS - Dependencies

[Java]

@Bean

public  ResourceProcessor<PagedResources<Resource<Taco>>>

tacoProcessor(EntityLinks links) {

return  new  ResourceProcessor<PagedResources<Resource<Taco>>>() {

@Override

public  PagedResources<Resource<Taco>> process(

PagedResources<Resource<Taco>> resource) {

resource.add(

links.linkFor(Taco.class)

.slash("recent")

.withRel("recents"));

return resource;

}

};

}

  

Ex.: [https://bit.ly/2lUM13d](https://bit.ly/2lUM13d)

Ex: [https://bit.ly/2lsQY36](https://bit.ly/2lsQY36) -> How to make an anonymous inner class as lambda

[Kotlin]

@Bean

fun vaccineProcessor(links:  EntityLinks):  ResourceProcessor<PagedResources<Resource<Vaccine>>> {

  

return  ResourceProcessor<PagedResources<Resource<Vaccine>>>  { resource ->

resource.add(

links.linkFor(Vaccine::class.java)

.slash("get").slash("all")

.withRel("geral").withSelfRel()

  

)

resource

}

}

  

  

The ResourceProcessor shown above is defined as an anonymous inner class and declared as a bean to be created in the Spring application context. Spring HATEOAS will discover this bean (as well as any other beans of type ResourceProcessor) automatically and will apply them to the appropriate resources. In this case, if a PagedResources<Resource<Taco>> is returned from a controller, it will receive a link for the most recently created tacos.

## Security

For new projects use @PreAuthorize instead of @Secured

[https://docs.spring.io/spring-data/rest/docs/current/reference/html/#security](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#security)

## Validation

  

## Metadata - JSON-SCHEMA

## Customizing repository exposure

[[https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.repository-exposure](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.repository-exposure)]

[[https://www.javabullets.com/4-ways-to-control-access-to-spring-data-rest/](https://www.javabullets.com/4-ways-to-control-access-to-spring-data-rest/)]

# RestTemplate

[https://www.baeldung.com/rest-template](https://www.baeldung.com/rest-template)

# Spring Data

## JSON Repository Populator

[https://www.baeldung.com/spring-data-jpa-repository-populators](https://www.baeldung.com/spring-data-jpa-repository-populators)

[https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.repository-populators](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.repository-populators)

# JPA Main Topics

## @Embeddable

[https://tomee.apache.org/examples-trunk/jpa-enumerated/](https://tomee.apache.org/examples-trunk/jpa-enumerated/)

[@Enumerated]

[JPA Annotations]

[https://www.baeldung.com/jpa-one-to-one](https://www.baeldung.com/jpa-one-to-one)

[composite foreign key]

[https://stackoverflow.com/questions/5721545/help-mapping-a-composite-foreign-key-in-jpa-2-0](https://stackoverflow.com/questions/5721545/help-mapping-a-composite-foreign-key-in-jpa-2-0)

[https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/](https://vladmihalcea.com/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/)

[OneToOne mapping]

[https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/](https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/)

  

# To Keep Information

[http://ilikearchitecture.net](http://ilikearchitecture.net/)

  
  
  
  
  
  
  

Swagger - Dependencies

  

  
  
  

Table - Example

  

  
  

![](https://lh6.googleusercontent.com/MTAt_Eu9MO_itTNE8LUEywSS87XmkYApSjS1nSljtadVMwnJR2y8Yp9uLKJ76k9r3BJ_VO0RNFG7Y5a-mNjSk2bMM6chN4OnHmIMnipikLFQCwAfdydHRanJ-WPrw97xMtPl0fJ0 "traço curto")
