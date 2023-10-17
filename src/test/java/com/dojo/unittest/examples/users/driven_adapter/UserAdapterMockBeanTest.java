package com.dojo.unittest.examples.users.driven_adapter;

import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driven_adapter.UserAutowiredAdapter;
import com.dojo.unittest.examples.user.driven_adapter.UserReactiveRepository;
import com.dojo.unittest.examples.user.driven_adapter.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserAdapterMockBeanTest {

    @MockBean
    UserReactiveRepository userReactiveRepository;

    @Autowired
    UserAutowiredAdapter userAdapter;

    @BeforeEach
    void setUp() {
        UserData userData = UserData
                .builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        when(userReactiveRepository.save(userData)).thenReturn(Mono.just(userData));
    }

    @Test
    void shouldSaveNewUser() {
        User user = User
                .builder()
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
