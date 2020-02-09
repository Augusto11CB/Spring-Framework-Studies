# Spring Security

Security is mostly about **authentication**, i.e. the verification of the identity, and **authorization**, the grant of access rights to resources.

Regarding authorization, three main areas are identified:
1.  Web requests authorization
2.  Method level authorization
3.  Access to domain object instances authorization

### Authentication

The basic interface is  `AuthenticationManager`  which **is responsible to provide an authentication method**. The  `UserDetailsService`  is the interface related to user’s information collection, which could be directly implemented or used internally in case of standard JDBC or LDAP methods.

### Authorization
The main interface is `AccessDecisionManager`; which implementations for all three areas listed above delegate to a chain of `AccessDecisionVoter`. Each instance of the latter interface represents an association between an `Authentication` (a user identity, named principal), a resource and a collection of `ConfigAttribute`, the set of rules which describes how the resource’s owner allowed the access to the resource itself, maybe through the use of user roles.

### UserDetails
By implementing  `org.springframework.security.core.userdetails.UserDetails` spring will recognize this *user class* as one to integrate with Spring Security

```java
public class User implements UserDetails {  
    //By implementing UserDatails spring will recognize this user class as one to integrate with Spring Security  
  
  @Id  
 @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
  @Column(unique = true)  
    @Size(min = 8, max = 20)  
    private String username;  
  
  @Column(length = 100)  
    private String password;  
  
  @Column(nullable = false)  
    private boolean enabled;  
  
  @OneToOne  
 @JoinColumn(name = "role_id")  
    private Role role;  
  
  @Override  
  public Collection<? extends GrantedAuthority> getAuthorities() {  
        List<GrantedAuthority> authorities = new ArrayList<>();  
  authorities.add(new SimpleGrantedAuthority(role.getName()));  
 return authorities;  
  }  
  
    @Override  
  public String getPassword() {  
        return null;  
  }  
  
    @Override  
  public String getUsername() {  
        return null;  
  }  
  
    @Override  
  public boolean isAccountNonExpired() {  
      return true;  
  }  
  
    @Override  
  public boolean isAccountNonLocked() {  
      return true;  
  }  
  
    @Override  
  public boolean isCredentialsNonExpired() {  
	  return true;  
  }  
  
    @Override  
  public boolean isEnabled() {  
	  return true;  
  }
```


### SecurityConfig -> Extending WebSecurityConfigurerAdapter
The `org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter` enables the **Spring Security Filter Chain**. This chain allow us filter certain requests so that user doesn't have to be authenticated (Ex: To access CSS)

#### AuthenticationManagerBuilder
Inside the class that extends WebSecurityConfigurerAdapter there is the possibility to **Autowire** the `AuthenticationManagerBuilder`. By doing this, We can setting up an class `UserService`, the one that implement `UserDetails` to be used in the process of authentication;

```java
@Autowired  
public void configureGloblal(AuthenticationManagerBuilder auth) throws Exception {  
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
}
```

#### Ignoring Authentication and Authorization When Acessing some Resource

```java
@Override  
public void configure(WebSecurity web) throws Exception {  
    web.ignoring().antMatchers("/assets/**");  // assets is a folter inside resource.static file
}  
//Requests that will require authentication and authorization
```

#### Configuring the Requests Flow in the System
When the `configure` method, whose parameter is` HttpSecurity`, is replaced, there is the possibility to configure the entire request flow. For example, configuring the logoff process, configuring the login page, restricting access to the resources to only those user that are authorized to do so, etc.

```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http
            .authorizeRequests()
                .anyRequest().hasRole("USER") // "ROLE_USER"
                //Until here, we implemented spring security, but all the access are restrict to those users that have the role USER.
                //Even the login page will be only available for those user. This is a problem as it prevents the login process, since in this step there is no user with the role ROLE_USER.
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
            .csrf();
    }
```

#### The cookie setted by Spring Security - JSESSIONID
When request and responses are occurring, the server requests the user to set a cookie with a specific JSESSIONID. The Browser automatically defines that cook and start using it for all request made for that domain. Each browser will have a unit JSESSIONID.

#### How to handle Successful Logins
```java
public AuthenticationSuccessHandler loginSuccessHandler() {  
    return (request, response, authentication) -> response.sendRedirect("/");  
}
```

#### How to handle Failed Logins
```java
public AuthenticationFailureHandler loginFailureHandler() {  
    return (request, response, exception) -> {  
        request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));  
  response.sendRedirect("/login");  
  };  
}
```
Here in the `loginFailureHandler` method when there is a failed login we create a attribute in the session called "flash". Depending on the business rule one action will be taken in order to gives continuity in the flow


#### Encrypt Pass Words  - PasswordEncoder
Every time authentication takes place, the submitted password will being encoded and, the encoded will be checked agains the database (this means that the password stored in the db must be encoded alson)

```java
@Autowired  
public void configureGloblal(AuthenticationManagerBuilder auth) throws Exception {  
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
}
```

#### Protection against CSRF
```java
@Override
    protected void configure(HttpSecurity http) throws Exception {
        // Here is possible configure all the flow of requests for instance, configure the log out process, configure the logon page etc

        //Restricting access to the resources to only those user that are authorized to do so;
        http
            .authorizeRequests()
                .anyRequest().hasRole("USER") // "ROLE_USER"
                //Until here we implemented spring security, but all the access are restrict to those users that have the role USER.
                //Even the login page will be only available for those user. This is a problem as it prevents the login process.
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
            .csrf(); 
    }
```

The **.csrf()** command  help in the security. It protects from csrf attacks that are a type of malicious exploit of a website where unauthorized commands are transmitted from a user that the web application trusts.

* How it works 
There are two values that must be compared when a request is made. One stays in the browser session the other stays in the server side in session data if they are not equal so the request is not accepted. It is called **Synchronizer token pattern**

