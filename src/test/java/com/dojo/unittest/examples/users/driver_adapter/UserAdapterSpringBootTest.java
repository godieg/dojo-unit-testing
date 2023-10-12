package com.dojo.unittest.examples.users.driver_adapter;

import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driver_adapter.UserAutowiredAdapter;
import com.dojo.unittest.examples.user.driver_adapter.UserReactiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserAdapterSpringBootTest {

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Autowired
    UserAutowiredAdapter userAdapter;

    @Test
    void shouldSaveNewUser() {
        User user = User
                .builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        Mono<User> userResponse = userAdapter.save(user);

        StepVerifier.create(userResponse)
                .expectNextMatches(usr -> {
                    assertThat(usr).isEqualTo(user);
                    Assertions.assertEquals("John", usr.getFirstName());
                    Assertions.assertEquals("Doe", usr.getLastName());
                    Assertions.assertEquals("john@example.com", usr.getEmail());
                    return true;
                })
                .verifyComplete();

        StepVerifier.create(userResponse)
                .expectNext(user)
                .expectComplete()
                .verify();
    }

}
