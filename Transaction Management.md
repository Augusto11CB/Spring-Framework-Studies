# Transaction Management

## Global Transactions & Local Transaction
A global transaction is a mechanism that allows a set of programming tasks, potentially using more than one resource manager and potentially executing on multiple servers, to be treated as one logical unit. Also, a global transaction may be composed of several local transactions, each accessing the same resource manager. The resource manager is responsible for performing concurrency control and atomicity of updates. A given local transaction may be either successful or unsuccessful in completing its access; it cannot be partially successful.

### What is a Transaction?
A database **transaction** is a sequence of actions that are treated as a single unit of work. These actions should either complete entirely or take no effect at all.

## Spring Framework’s declarative transaction
**What is declarative transactions?** This means you separate transaction management from the business code. You only use annotations

By using Spring Boot project with spring-data* or spring-tx dependencies, then transaction management will be enabled by default. A transaction has the ACID concept

**Atomicity** – A transaction should be treated as a single unit of operation, which means either the entire sequence of operations is successful or unsuccessful.

**Consistency** - Ensures that a transaction can only bring the database from one valid state to another

**Isolation** – There may be several transactions processing with the same data set. Each transaction should be isolated from others to prevent data corruption

**Durability** – Once a transaction has completed, its result must be made permanent and cannot be removed from the db due failure;

In order to enable transactional management **@Transactional** Annotation is used either at **class** or **method** level

The annotation supports further configuration as well:

-   the **Propagation** **Type** of the transaction - [spring-propagation-example](https://stackoverflow.com/questions/25076718/spring-propagation-examples-in-laymans-terms/25083505#25083505) (rephrase the explanation)
-   the **Isolation** **Level** of the transaction
-   a **Timeout** for the operation wrapped by the transaction
-   a **readOnly** flag – a hint for the persistence provider that the transaction should be read only
-   the **Rollback** rules for the transaction

#### Rollback Information
 By default, rollback just happens for runtime, unchecked exceptions. In other words, the **checked exceptions does not trigger rollbacks** of transactions.

Regarding the rollback, is possible to enable rollbacks for checked exceptions using the following annotation’s parameters **rollbackFor(*Optional array of exception classes that must cause rollback.*)** and **noRollbackFor(*Optional array of exception classes that must not cause rollback.*)**.

Ex.: **@Transactional(rollbackFor = Exception.class)**

#### Isolation information
There is three types of isolation as you can see below:

**Dirty** **reads**: read UNCOMMITED data from another transaction

**Non**-**repeatable** reads: read COMMITTED data from an UPDATE query from another transaction

**Phantom** reads: read COMMITTED data from an INSERT or DELETE query from another transaction

## Transactions and Proxies

For every class annotated with @Transactional Spring creates a proxy. Even if the annotation is on methods or on class level. So that only external method calls that come in through the proxy will be intercepted. Any self-invocation call will not start any transaction, even if the method has the @Transactional annotation.

## References
[https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/transaction.html](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/transaction.html)

[https://www.tutorialspoint.com/spring/spring_transaction_management.htm](https://www.tutorialspoint.com/spring/spring_transaction_management.htm)

[https://www.baeldung.com/transaction-configuration-with-jpa-and-spring](https://www.baeldung.com/transaction-configuration-with-jpa-and-spring)

Java Dynamic Proxy -> [https://spring.io/blog/2012/05/23/transactions-caching-and-aop-understanding-proxy-usage-in-spring](https://spring.io/blog/2012/05/23/transactions-caching-and-aop-understanding-proxy-usage-in-spring)
