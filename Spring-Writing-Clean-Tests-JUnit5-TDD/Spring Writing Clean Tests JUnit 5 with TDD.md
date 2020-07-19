
## Annotations

### @TestPropertySource
This annotation allows to define the property files to use for the test class annotated by with it. Properties defines in these properties files will override propertoes defines elsewhere.
`@TestPropertySource(locations = "classpath:test.properties")`

## MockMvc - RESTful Testing
**MockMVC**  class is part of Spring MVC test framework which helps in testing the controllers explicitly starting a Servlet container. With MockMvc, . is possible to test a fully functional REST controller but without the overhead of deploying the app to a container.

MockMVC stands up controller, sends requests and then expects for a response.

**Main Features**
- Executes Request
- Validates HTTP Response
- Validate HTTP Headers
- Validate Response Body

```java
@Autowired 
private MockMvc mockMvc;
```
## DBUnit - Testing Repository Classes 
DbUnit is a JUnit extension, and a unit testing tool used to **test**  **relational database interactions** in **Java.**

### DBUnit - Configuration
1. define a **database schema**
file location: _src/main/resources/schema.sql_
	```
	  CREATE TABLE IF NOT EXISTS products (  
	    id BIGINT NOT NULL AUTO_INCREMENT,  
	    name VARCHAR(128) NOT NULL,  
	    quantity INTEGER NOT NULL,  
	    version INTEGER NOT NULL,  
	    PRIMARY KEY (id)  
	);
	```

2. Defining the Initial Database Contents
file location: _src/test/resources/datasets/products.yml_
	```yaml
	products:  
	  - id: 1  
	    name: "P1"  
	  quantity: 10  
	    version: 1  
	  - id: 2  
	    name: "P2"  
	  quantity: 5  
	    version: 2
	```

3. creating a test config for Initializing the Database Connection and Schema
	```java
	@Configuration
	@Profile("test")
	public class ProductRepositoryTestConfiguration {

	    @Primary
	    @Bean
	    public DataSource dataSource() {


	        DriverManagerDataSource dataSource = new DriverManagerDataSource();

	        dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");

	        return dataSource;
	    }
	}
	```

4. Writing test class
- add `DBUnitExtension` class
- add `connectionHolder` method
- add annotation `@DataSet` in the test methods to load the content declared there in the DB instance
	```java
	@ExtendWith({SpringExtension.class, DBUnitExtension.class})
	@ActiveProfiles("test")
	@SpringBootTest
	public class ProductRepositoryTest {

	    @Autowired
	    private DataSource dataSource;

	    @Autowired
	    private ProductRepository repository;

	    public ConnectionHolder getConnectionHolder() throws SQLException {
	        // RETURNS A FUNCTION THAT RETRIEVES A CONNECTION FROM OUR DATA SOURCE
	        return () -> dataSource.getConnection();
	    }

	    @Test
	    @DataSet("products.yml")
	    void testFindAll() {
	        List<Product> products = repository.findAll();
	        Assertions.assertEquals(2, products.size());
	    }

	```
**PS:** All these implementations is located in the project: [Product-Microservice](https://github.com/AugustoCalado/Bueno-Eletronics-Ecommerce/tree/master/Product-Microservice)

## 	Testing MongoDB Repository
- @DataMongoTest: Loads an embedded MongoDB instance
- MongoTemplate:  is a spring data class that makes interaction with MongoDB

```java
class ReviewReposit
```
### Importing Data from JSON to MongoDB
**Samples Within json file**
- location: src/test/resources/data

**Transform content in the json into Objects using Jackson**
```java
private static File SAMPLE_JSON = Paths.get("src","test","resources", "data", "sample.json").toFile();
Review[] objects = mapper.readValue(SAMPLE_JSON, Review[].class);

```

**Save Objects to MongoDB using MongoTemplate**
```java
Arrays.stream(objects).forEach(mongoTemplate::save);
```

#### Example - Setup(Before test) and cleaning(After Test) MongoDB Collection 
**Context:** 
- Loading data from JSON. 
	-  Create a custom way to load different json files from each test run independently
	- Implementation of an annotation that interacts with the MongoSpringExtesion that provides information about the test MongoDB  JSON file for this method as well as the collection name and type of objects stored in the test file.
- Extract this setup and cleaning configuration from the test class
	- Create an class that implements **Junit Extension Points** (BeforeEachCallback, AfterEachCallback)

**Annotation**
```java
public class MongoSpringCustomExtension implements BeforeEachCallback, AfterEachCallback {

    /**
     * Path to where our test JSON files are stored
     */
    private static Path JSON_PATH = Paths.get("src", "test", "resources", "data");

    private final ObjectMapper mapper = new ObjectMapper();


    /**
     * Called before each test executes. This callback is reponsible for importing the JSON document,
     * defined by the MongoDataFile annotation, into the embedded MongoDB, through the provided MongoTemplate.
     *
     * @param context The ExtensionContext which provides access to the test method
     */
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        context.getTestMethod().ifPresent(method -> {

            // Load test file from annotation
            MongoDataFile mongoDataFile = method.getAnnotation(MongoDataFile.class);

            // Load the MongoTemplate that can be used to import the data
            getMongoTemplate(context).ifPresent(mongoTemplate -> {
                try {
                    List objects = mapper.readValue(
                            JSON_PATH.resolve(mongoDataFile.value()).toFile(),
                            mapper.getTypeFactory().constructCollectionType(List.class, mongoDataFile.classType())
                    );

                    //Load each review into MongoDB
                    objects.forEach(mongoTemplate::save);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        });
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        context.getTestMethod().ifPresent(method -> {

            // Load test file from annotation
            MongoDataFile mongoDataFile = method.getAnnotation(MongoDataFile.class);

            // Load the MongoTemplate that can be used to import the data
            getMongoTemplate(context).ifPresent(mongoTemplate -> mongoTemplate.dropCollection(mongoDataFile.collectionName()));

        });
    }

    /**
     * Helper method that uses reflection to invoke the getMongoTemplate() method on the test instance
     *
     * @param context The ExtensionContext which provides access to the test method
     */
    private Optional<MongoTemplate> getMongoTemplate(ExtensionContext context) {

        //get class -> get class's method -> get instance -> invoke the retrieved method in the retrieved instance

        Optional<Class<?>> testClass = context.getTestClass();
        if (testClass.isPresent()) {
            // get class ->
            Class<?> clazz = testClass.get();
            try {
                // get class's method
                Method method = clazz.getMethod("getMongoTemplate", null);
                // get instance ->
                Optional<Object> testInstance = context.getTestInstance();
                if (testInstance.isPresent()) {
                    //  invoke the retrieved method in the retrieved instance
                    return Optional.of((MongoTemplate) method.invoke(testInstance.get(), null));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

}
```
**Finally - TestClass**
```java
@DataMongoTest // -> Loads an embedded MongoDB instance
@ExtendWith(MongoSpringCustomExtension.class)
public class ReviewRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Test
    @MongoDataFile(value = "sample.json", classType = Review.class, collectionName = "Reviews")
    void testFindAll() {
        List<Review> reviews = reviewRepository.findAll();
        assertEquals(2, reviews.size());
    }
}
```

**PS:** All these implementations is located in the project: [Review-Microservice](https://github.com/AugustoCalado/Bueno-Eletronics-Ecommerce/tree/master/Review-Microservice)

## Testing Third-party API
### WireMock
WireMock allows stub HTTP responses for use in tests environments, as well as unit tests. 

#### Example
**Request made by a service**
```java
public Optional<InventoryRecord> purchaseProduct(Long id, Integer quantityPurchased) {
        try {
            return Optional.ofNullable(restTemplate.postForObject(
                    baseUrl + "/" + id + "/purchaseRecord",
                    new PurchaseRecord(id, quantityPurchased),
                    InventoryRecord.class
            ));
        } catch (HttpClientErrorException e) {
            return Optional.empty();
        }

 }
```

#### WireMock Config
**Import WireMock**
```
testImplementation "com.github.tomakehurst:wiremock-jre8:2.27.0"
```

**Externalized WiredMock Response Objects - File Location**
```json
// location where the expected response are stored
src/test/resources/__files/json/inventory-response-after-post.json
src/test/resources/__files/json/inventory-response.json
```

**Not Externalized Approach**
```java
private WireMockServer wireMockServer;  
  
@BeforeEach  
void beforeEach() {  
  
    wireMockServer = new WireMockServer(9999);  
    wireMockServer.start();
    
// configure the requests - Not externalized approach

        wireMockServer.stubFor(get(urlEqualTo("/inventory/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("json/inventory-response.json")
                )
        );

        wireMockServer.stubFor(get(urlEqualTo("/inventory/2"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                )
        );

        wireMockServer.stubFor(post(urlEqualTo("/inventory/1/purchaseRecord"))
                .withHeader("Content-Type", containing("application/json"))

                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("json/inventory-response-after-post.json")
                )
        );
}
```


**Externalized WiredMock Requests - File Location**
```
/**  
 * When wireMock starts, it automatically looks in the mapping directory, finds the mapping files,
 * and configures the request and responses based on the found mapping files 
 *  */
src/test/resources/mappings/get-inventory-1.json
src/test/resources/mappings/get-inventory-2.json
src/test/resources/mappings/post-inventory-1-purchaseRecord.json
```

```java
private WireMockServer wireMockServer;  
  
@BeforeEach  
void beforeEach() {  
  
    wireMockServer = new WireMockServer(9999);  
    wireMockServer.start();
```

**Unit Test Using WireMock**

```java
    @Test
    void testPurchaseProductSuccess() {

        Optional<InventoryRecord> record = service.purchaseProduct(1L, 1);

        assertTrue(record.isPresent());
        assertEquals(499, record.get().getQuantity().intValue());
    }
```
#### json/inventory-response.json
PS: Same json structure for `inventory-response-after-post.json`
```json
{  
  "productId": 1,  
  "quantity": 500,  
  "productName": "Nice Name",  
  "productCategory": "Great Product"  
}
```

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRecord implements Serializable {
    private Long productId;
    private Integer quantity;
    private String productName;
    private String productCategory;
}
```

#### get-inventory-1.json
```json
{  
  "request": {  
    "method": "GET",  
  "urlPattern": "/inventory/1"  
  },  
  "response": {  
    "status": 200,  
  "bodyFileName": "json/inventory-response.json",  
  "headers": {  
      "Content-Type": "application/json"  
  }  
  }  
}
```
#### post-inventory-1-purchaseRecord.json
```json
{  
  "request": {  
    "method": "POST",  
  "urlPattern": "/inventory/1/purchaseRecord"  
  },  
  "response": {  
    "status": 200,  
  "bodyFileName": "json/inventory-response-after-post.json",  
  "headers": {  
      "Content-Type": "application/json"  
  }  
  }  
}
```

**PS:** All these implementations is located in the project: [Inventory-Microservice](https://github.com/AugustoCalado/Bueno-Eletronics-Ecommerce/tree/master/Inventory-Microservice)
