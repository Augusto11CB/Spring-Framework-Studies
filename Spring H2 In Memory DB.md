##  Create schema and insert data on initialization

Create schema.sql and data.sql to initialize database with some fixed schema (DDL) and insert default data (DML) into tables before the application is ready is run business usecases.

It can be achieved by putting sql files into resources folder (`/src/main/resources/`).

-   **schema.sql**  – To initialize the schema ie.create tables and dependencies.
-   **data.sql**  – To insert default data rows.

schema.sql
```sql
DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (

id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(``250``) NOT NULL,
last_name VARCHAR(``250``) NOT NULL,
email VARCHAR(``250``) DEFAULT NULL
);
```

data.sql
```sql
INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) VALUES
  ('Lokesh', 'Gupta', 'abc@gmail.com'),
  ('Deja', 'Vu', 'xyz@email.com'),
  ('Caption', 'America', 'cap@marvel.com');
  ```

## Configuration Properties
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

#Enabling H2 Console
spring.h2.console.enabled=true
 
#Custom H2 Console URL
spring.h2.console.path=/h2
```
