package com.dojo.unittest.examples.users.driven_adapter;

import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driven_adapter.UserAutowiredAdapter;
import com.dojo.unittest.examples.user.driven_adapter.UserReactiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcConnectionDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class UserAdapterContainersTest {

    @Container
    @ServiceConnection(type = R2dbcConnectionDetails.class)
    static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:16.0")
            .withDatabaseName("unittest")
            .withUsername("unittest")
            .withPassword("secret")
            .waitingFor(Wait.forListeningPort())
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource(
                            "schema-postgresql.sql"),
                    "/docker-entrypoint-initdb.d/schema-postgresql.sql");

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Autowired
    UserAutowiredAdapter userAdapter;

    @Test
    void shouldVerifyContainerIsRunning() {
        assertThat(postgresqlContainer.isRunning()).isTrue();
    }

    @Test
    void shouldSaveNewUser() throws InterruptedException {
        Thread.sleep(10000);

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
    }

}
