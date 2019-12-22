# Build Cloud Services
Build Cloud Services

## Week1

### Protocol
Protocol tells us 'how' comunicate with another entity. It defines a syntax (the message format), semantics (the meaning of the message) and timing.

### HTTP
Application Protocol for communicating between devices on a network
Http is a client-server Protocol -> The iteraction of both (client and server), are always initiated by a client that wants to access or request some *resource* on the server.

#### Why Use Protocol HTTP
1. With that we've got a *uniform interface* that we can provide via HTTP to the services or resources that are sitting on the server;
2. There is the sessions concept - the action of remembering when people request something, who that person is and what they previously requested and what data is associated with them;
3. Data marshalling - convert http to another format (the format processed by the Application);
4. Lots of libraries and infrastructure that suports http.

#### HTTP Request Methods
Every request has a request methods. Request methods is the 'action' that the client is asking the server to take on its behalf. Every request involves a resource.
A resource is typically specified as a path to  a resource on the server

**GET** -> ask the server to get some resource and return it for us - can send a small quantity of data to the server;
**POST** -> Used when we send data to the server;
**PUT** -> Used when we ask to the server to store some data that is contained withn the request;
**DELETE** -> delete some information in the server.

#### HTTP Anatomy
How is the HTTP format? It consists of several parts. Ones are mandatory others are optional.

1. Request Line - Specifies the *request method* and the *resource's* path
2. Headers - [META INFORMATION] Extra information to "help" the server figure out the right way to process the request. 
    Language we'd like the reponse to come back
    character set.
    content type - What are sending back to the cliente (json)
3. Body - The body is any piece of data that the client is sending to the server.
    The Body is the data the client is sending, that the server absolutely has to have in order to complete that Request.

### URLs & Query Parameters
In HTTP, the way that resources are identified is with what is called a *uniform resource locator* or URL.
    URL is consists of "http://host:port/path"

#### Queries parameters 
It is additional information that can be attached to the end of the URL.
* ``'http://host:port/path?a=b'    ## Queries Parameters = ?a=b``

* ``'http://host:port/path?a=b&bla=acdc' ## Queries Parameters = ?a=b&bla=acdc   #a=b and bla=acdc ``

#### Cares to take when passing Queries parameters
When passing queries parameters we can't have *certain special* characters appear within our query parameter values and keys. To solve that we use the URL Encoded to converte not allowed characteres.

**URL Encoder Example:**
* https://www.urlencoder.org/
* https%3A%2F%2Fwww.urlencoder.org%2F

### Mime Types & Content Type Headers

**How to interpret the data send within the http request body? **
> The way it is done is through what's called MIME Types. 
    
MIME Type is an identifier for a particular type or format of information. They describe the body of the request.

Ex: 
    image/jpg
    image/png
    text/html

client and the server are supposed to look at these MIME types, and then based on this, decide how to interpret the data.

#How do we comunicate those MIME Types?
They are passed within the Headers of http messages [META INFORMATION] as a parameter of Content-Type 

##Body of HTTP
Mechanism to send data to the server.
URL Encode (the thing we se a key and a value and a & key and another value a=b&c=d...) are used in the body to send information.

Also, in the body we can send files like images/large amount datas using the MIME type Multipart Encode, where the body is broken into a series of parts, each of which can have its own identifier.

##HTTP Response Anatomy 
One of the important things that we have to consider when we're sending messages to some remote server and asking it to, give us a resource or take some action on that resource, is what happened on the server.
To meet that need the HTTP Response takes the responsability to performe this feedback from server -> Status Line

#Status Line
Status Line  tells us what happened or what is going on. 
[Response code] [Phrase or Text]
1XX - INFORMATION
2XX - SUCCESS
3XX - REDIRECTION
4XX - CLIENT ERROR
5XX - SEVER ERROR

#Response Header
It contains the information of how to interpret the data send back to us from the server. Content-Type

#Response Body
Contains the actuall resource itself

Example -> Get a Image from the server
Status Line 200
Response Header: image/jpe
Response Body: Binary data of the image

#Cookies
Cookies are small files which are stored on a user's computer. They are designed to hold a modest amount of data specific to a particular client and website.
Each cookie is effectively a small lookup table containing pairs of (key, data) values - for example (firstname, John) (lastname, Smith). Once the cookie has been read by the code on the server or client computer, the data can be retrieved and used to customise the web page appropriately.
Cookies are a convenient way to carry information from one session on a website to another, or between sessions on related websites.

##HTTP Polling
Discussed different approachs to stabilish connection
Problem faced:  The client never knew what was going on in the server, it didn't know when it should go and poll the server to pick up information.

##Push Notifications
The client sets up a persistent connection to severs. And this connection is done over XMPP, which is an XML based messaging protocol, that was originally used for chat messages.
So the ideia is that at any time the server has access to the  client over XMPP and it can push information to it.

#Push To Sync
Used to transfer large amounts of data or when there is the necessity to be in strict control of data dissemination and security



Week2

###Servlet
It is a java Object, that has special methods for handling incoming HTTP Requests (doGet(), doPost(), doDelete()....)

What Does Severvlets do? 
When a browser sends an HTTP request. This request gets routed to a WEB Application Container. Inside an web conteiner there are several servlests that is going to handle those http request.

How we know how Servlet is going to handle our http request?
There is a router that based on the request will decide which servlet will handle the request.
    Java Case
    There is a file called Web.xml, that file tells the Web Container which resquest goes to which servlet 


###Spring 

Spring Boot & Application Structure:
1. setup web container
2. discover our controllers
3. setup dispatcher servelet
4. connecting to db, etc

SpringApplication.run - The main method has the following annotations
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@ComponentScan({"com.bla.blu", "com.cla.tle"})
@PropertySource("classpath:/properties/my-properties-name-${spring.profiles.active}.properties")



@Bean #ObjectMapper
@Bean #ModelMapper
@Bean #Jackson2JsonMessageConverter

##Web.xml
Thanks spring boot we don't have a web.xml file anymore. We've removed that extra piece of infrastructure that we had to have and understand how to build to tell the servlet container about our servlets and how to map requests into them.
Now, every information about how to contruct our application is defined in the Application main class (SpringApplication.run), 


##RequestParam x PathVariable 
@PathVariable -> The value informed is part of the URL structure.**It is part of the path**

    @RequestMapping(value="/{type}/{post}", method=RequestMethod.GET)
    public String post(@PathVariable("type") String type, @PathVariable("post") String post)
    Example: http://localhost:8080/niceType/NicePost 

@RequestParam -> They are URL parameters, but is not part of URL structure. We use question marks to introduce those parameters
    @RequestMapping("/findByDescricao")
    public List<MeuObjeto> findByDescricao(@RequestParam("description") String description)

    Example: http://localhost:8080/findByDescricao?description=MyDesc 

##Client Data with Request Body

@RequestBody


##Handling MultipartData
Context: Send a large video to the server. How do we do this and how do we process this?
First thing, we send this data through large sets of binary data. Also, we do not want to keep all those binary date in memory while we are receiving the remain video parts.
So, we just send those date directly to the disk. 

How the controller is that handle all these multipart data? 
public boolean upload video(@RequestParam("data", multipartfile videoData) {
 InputStream in =  videoData.getInputStream
 //Save it to disk
}

##Spring Accepting MultipartData
We need add the follow code snippet  in our config

@Bean
public MultipartConfigElement multipartConfigElement(GraphQLProperties graphQLProperties) {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DEFAULT_UPLOAD_MAX_FILE_SIZE);
    factory.setMaxRequestSize(DEFAULT_UPLOAD_MAX_REQUEST_SIZE);

    String temp = graphQLProperties.getServer().getUploadMaxFileSize();
    if (StringUtils.hasText(temp))
        factory.setMaxFileSize(temp);

    temp = graphQLProperties.getServer().getUploadMaxRequestSize();
    if (StringUtils.hasText(temp))
        factory.setMaxRequestSize(temp);

    return factory.createMultipartConfig();
}

##Sending Response to the client (@ResponseBody)

#WayOne
@RequestMapping(value = "/user/all", method = RequestMethod.GET)
public @ResponseBody List<User> listAllUsers() {
    return userService.findAllUsers();
}

#WayTwo
@RequestMapping(value="/user/create", method=RequestMethod.POST)
public ResponseEntity<DTO> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
//SomeCode
    return new ResponseEntity<DTO>(myDTO,HttpStatus.CONFLICT);
}

PS:"mMrshalling" refers to the process of converting the data or the objects into a byte-stream, and "unmarshalling" is the reverse process of converting the byte-stream back to their original data or object. The conversion is achieved through "serialization".

##Custom Marshalling with Jackson Annotations
How spring converts our objects that we return from controller methods or that we pass into controller methods, back and forth from JSON ?
To allow those conversions, Spring uses a open source project called Jackson (open source project)

ObjectMapper -> One of its functions is  to convert object to json and json to object
### ObjectMapper
How ObjectMapper does those convertions?
First, it expects that the class we want to convert to json has a constructor that takes no arguments. So that it uses one of its methods that go through the JSON's fields looking for  fields that match with the name of the class's variable to set it up.

###Annotations Jackson
@JsonIgnore - Ignore a field annotated with it.


## Stateful x Stateless
A stateful application is when the server remebers things about the client. If we have a server that's not remembering anything about the clients it's talking to, it's stateless.

To scale is important to understand both concepts. An stateless server can be a better solution when scaling is mandatory, because it is easier to scale, since we don't need to remember nothing about the client, than stateful servers. In the case of the stateful server, to scale that, we need
to determine which of the clients is going to be migrated and with data is important to send along this migration about the client.

Sticky sessions: When we have a stateful server, one possible approach is to hook a clientA into a server. That server will be responsible for handling the requests from clientA. 

### IaaS x PaaS
Infrastructure as a Service x Platform as a Service
Very configurable x Standart configurations


## Spring Dependency Injection & Auto-wiring
Dependency injection is, is we can define, objects, that a particular class depends on.
We say that an object is a dependency of a class annotating that with @Autowired -> That annotation tells spring to look through the configuration that's provided to it, and it's going to try to find an implementation of that dependency. 
If Spring finds, it is going to automatically create an instance of the dependent object  (or in some cases reuse an existing one) and inject it.

Normally, we can define those objects that are dependencies of other classes in spring inside classes annotated with the annotation @Configuration. When Spring start up, it looks at all the classes annotated with @Configuration scanning for beans definition.
Those beans (methods annotated with @Beans inside some class annotated wi), actually, are the objects that our classes need to perform some of their action. 
