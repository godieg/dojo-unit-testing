package com.dojo.unittest.examples.users.domain;

import com.dojo.unittest.examples.user.domain.UserRepository;
import com.dojo.unittest.examples.user.domain.UserUseCase;
import com.dojo.unittest.examples.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    UserRepository userRepository;

    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userUseCase = new UserUseCase(userRepository);
    }

    @Test
    void shouldSaveNewUser() {
        var user = User
                .builder()
                .firstName("John")
                .lastName("Doe")
                .email("example@example.com")
                .build();

        given(userRepository.save(user)).willReturn(Mono.just(user.toBuilder().id(1L).build()));

        Mono<User> userResponse = userUseCase.save(user);

        StepVerifier.create(userResponse)
                .expectNextMatches(usr -> {
                    assertThat(usr.getId()).isEqualTo(1L);
                    return true;
                })
                .verifyComplete();
    }

}
