package com.dojo.unittest.examples.users.entry_point;

import com.dojo.unittest.examples.user.domain.UserUseCase;
import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.entry_point.UserHandler;
import com.dojo.unittest.examples.user.entry_point.UserRouter;
import com.dojo.unittest.examples.user.entry_point.dto.UserRequest;
import com.dojo.unittest.examples.user.entry_point.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebFluxTest(controllers = {UserRouter.class, UserHandler.class})
class UserHandlerTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private UserUseCase userUseCase;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .build();
    }

    @Test
    void shouldSaveUser() {
        var user = User
                .builder()
                .firstName("John")
                .lastName("Doe")
                .email("example@example.com")
                .build();

        var userRequest = UserRequest
                .builder()
                .firstName("John")
                .lastName("Doe")
                .email("example@example.com")
                .build();

        given(userUseCase.save(user)).willReturn(Mono.just(user.toBuilder().id(1L).build()));

        webTestClient.post()
                .uri("/api/v1/users")
                .bodyValue(userRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UserResponse.class)
                .value(response -> {
                    assertThat(response.getId()).isEqualTo(1L);
                    assertThat(response.getFirstName()).isEqualTo("John");
                    assertThat(response.getLastName()).isEqualTo("Doe");
                    assertThat(response.getEmail()).isEqualTo("example@example.com");
                });

        verify(userUseCase, times(1)).save(user);
    }

}
