package aug.bueno.spring.automated.testing.testing.repo;

import aug.bueno.spring.automated.testing.testing.CustomerServiceApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerServiceApplication.class)
@ContextConfiguration(initializers = ITCustomerRepoAbstract.Initializer.class)
public abstract class ITCustomerRepoAbstract {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "testcontainers=" + applicationContext.getEnvironment(),
                    "spring.datasource.url=jdbc:tc:postgresql://localhost:" + postgres.getExposedPorts().get(0) + "/test?TC_INITSCRIPT=init_customerdb.sql",
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword(),
                    "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
            );
        }
    }

    private static PostgreSQLContainer postgres;

    static {
        postgres = (PostgreSQLContainer) new PostgreSQLContainer().withDatabaseName("integration-tests-db")
                .withUsername("sa")
                .withPassword("sa")
                .withClasspathResourceMapping("init_customerdb.sql",
                        "/scripts/sql/init.sql",
                        BindMode.READ_ONLY);

        postgres.start();

    }

    @Autowired
    protected CustomerRepo repo;

}