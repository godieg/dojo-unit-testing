package com.dojo.unittest.examples.user.entry_point;

import com.dojo.unittest.examples.user.domain.UserUseCase;
import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.entry_point.dto.UserRequest;
import com.dojo.unittest.examples.user.entry_point.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase userUseCase;

    public Mono<ServerResponse> save(UserRequest userRequest) {
        return userUseCase
                .save(mapUserRequestToUser(userRequest))
                .map(UserHandler::mapUserToUserResponse)
                .flatMap(userResponse -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(userResponse));
    }

    private static User mapUserRequestToUser(UserRequest user) {
        return User
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    private static UserResponse mapUserToUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
