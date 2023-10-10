package com.dojo.unittest.examples.user.domain;

import com.dojo.unittest.examples.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userGateway;

    public Mono<User> save(User user) {
        return userGateway.save(user);
    }

}
