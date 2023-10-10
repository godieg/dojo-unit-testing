package com.dojo.unittest.examples.user.domain;

import com.dojo.unittest.examples.user.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

}

