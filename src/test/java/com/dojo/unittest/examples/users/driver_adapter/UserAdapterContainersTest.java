package com.dojo.unittest.examples.users.driver_adapter;

import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driver_adapter.UserAutowiredAdapter;
import com.dojo.unittest.examples.user.driver_adapter.UserReactiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.PropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@PropertySource("classpath:application-containers.properties")
public class UserAdapterContainersTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:16.0")
            // .withDatabaseName("unittest")
            // .withUsername("unittest")
            // .withPassword("secret")
            // .withInitScript("schema-postgres.sql")
            ;

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Autowired
    UserAutowiredAdapter userAdapter;

    @Test
    void shouldVerifyContainerIsRunning() {
        assertThat(postgresqlContainer.isRunning()).isTrue();
    }

    @Test
    void shouldSaveNewUser() {
        User user = User
                .builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        User userExpected = User
                .builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        Mono<User> userResponse = userAdapter.save(user);

        StepVerifier.create(userResponse)
                .expectNextMatches(usr -> {
                    assertThat(usr).isEqualTo(userExpected);
                    Assertions.assertEquals("John", usr.getFirstName());
                    Assertions.assertEquals("Doe", usr.getLastName());
                    Assertions.assertEquals("john@example.com", usr.getEmail());
                    return true;
                })
                .verifyComplete();

        StepVerifier.create(userResponse)
                .expectNext(userExpected)
                .expectComplete()
                .verify();
    }


}
