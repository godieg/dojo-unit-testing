package com.dojo.unittest.examples.users.driven_adapter;

import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driven_adapter.UserAdapter;
import com.dojo.unittest.examples.user.driven_adapter.UserReactiveRepository;
import com.dojo.unittest.examples.user.driven_adapter.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserAdapterMockitoTest {

    @Mock
    private UserReactiveRepository userReactiveRepository;

    private UserAdapter userAdapter;

    @BeforeEach
    void setUp() {
        userAdapter = new UserAdapter(userReactiveRepository);
    }

    @Test
    void shouldSaveNewUser() {
        User user = User
                .builder()
                .firstName("John")
                .lastName("DOE")
                .email("john@example.com")
                .build();

        UserData userData = UserData
                .builder()
                .firstName("John")
                .lastName("DOE")
                .email("john@example.com")
                .build();

        given(userReactiveRepository.save(userData)).willReturn(Mono.just(userData));

        Mono<User> userResponse = userAdapter.save(user);

        StepVerifier.create(userResponse)
                .expectNextMatches(usr -> {
                    assertThat(usr).isEqualTo(user);
                    Assertions.assertEquals("John", usr.getFirstName());
                    Assertions.assertEquals("DOE", usr.getLastName());
                    Assertions.assertEquals("john@example.com", usr.getEmail());
                    return true;
                })
                .verifyComplete();

        StepVerifier.create(userResponse)
                .expectNext(user)
                .expectComplete()
                .verify();

        verify(userReactiveRepository, times(1)).save(userData);
    }


    @Test
    void shouldToUpperCaseText() {
        var result = ReflectionTestUtils.invokeMethod(userAdapter, "toUpperCase", "test");

        assertThat(result).isEqualTo("TEST");
    }
}
