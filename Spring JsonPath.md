
# Spring JsonPath - Write Clean Assertions with JsonPath
JsonPath provides an easy and readable way to extract parts of a JSON document.
> org.springframework.test.web.servlet.result.MockMvcResultMatchers#jsonPath(java.lang.String, java.lang.Object...)


**Example**

- Perform a POST request to the url ‘/api/todo’.
- Verify that the content type of the response is ‘application/json’
- Fetch the field errors by using the JsonPath
- Fetch the field errors by using the JsonPath expression $.fieldErrors and ensure that two field errors are returned.
- Use the JsonPath expression $.fields[*].xyz to fetch all available xyz's. Ensure that field errors about title and description fields are available.
- Use the JsonPath expression $.fields[*].abc to fetch all available abc's. Ensure that error messages concerning title and description fields are returned.

```json
{
   "field":[
      {
         "xyz":"description",
         "abc":"The maximum length of the description is 500 characters."
      },
      {
         "xyz":"title",
         "abc":"The maximum length of the title is 100 characters."
      }
   ]
}
```

```java
@Test  
public void test() throws Exception {  
  mockMvc.perform(post("/api/todo")  
         .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)  
         .body(IntegrationTestUtil.convertObjectToJsonBytes(added))  
         .with(userDetailsService(IntegrationTestUtil.CORRECT_USERNAME))  
   )  
         .andExpect(status().isBadRequest())  
         .andExpect(content().mimeType(IntegrationTestUtil.APPLICATION_JSON_UTF8))  
         .andExpect(jsonPath("$.field", hasSize(2)))  
         .andExpect(jsonPath("$.field[*].xyz", containsInAnyOrder("title", "description")))  
         .andExpect(jsonPath("$.field[*].abc", containsInAnyOrder(  
               "The maximum length of the description is 500 characters.",  
  "The maximum length of the title is 100 characters."  
  )));  
}
```
