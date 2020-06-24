
# Spring Rate Limiting Guava

### Configuration
```java
@Bean
public  RateLimiter  rateLimiter(){

    return  RateLimiter.create(
                /*permitsPerSecond:*/0.1d

                Duration.ofSeconds(30)
            )
}
```

### Controller
```java
@RestController  
public class ORZController{  
  
    @GetMapping  
  RateLimiter rateLimiter;  
  
  
  @GetMapping("wait")  
    public ResponseEntity waitToProceedIfRateLimitingWasAchieved(){  
        rateLimiter.acquirer(); //Hold the request until the availability of an gap to proceed with the flow  
  return ResponseEntity.status(HttpStatus.CREATED).build()  
    }  
  
    @GetMapping("/can-be/to-many-requests")  
    public ResponseEntity tryToProceedIfTheRateLimitingWasNotAchievedOtherWiseReturn429(){  
  
        boolean okToGO = rateLimiter.tryAcquire();  
  
 if(okToGO){  
            //DoSomeStuff  
  } else {  
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build()  
        }  
    }  
  
}
```

## References
[Mike  Nielsen - Spring Boot Rate Limiting with guava](https://www.youtube.com/watch?v=JOnsCCZOF8M)
