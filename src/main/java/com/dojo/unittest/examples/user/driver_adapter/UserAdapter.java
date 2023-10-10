package com.dojo.unittest.examples.user.driver_adapter;

import com.dojo.unittest.examples.user.domain.UserRepository;
import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driver_adapter.model.UserData;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserReactiveRepository userReactiveRepository;

    @Override
    public Mono<User> save(User user) {
        UserData userToSave = UserData
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return userReactiveRepository
                .save(userToSave)
                .map(usr -> User
                        .builder()
                        .id(usr.getId())
                        .firstName(usr.getFirstName())
                        .lastName(usr.getLastName())
                        .email(usr.getEmail())
                        .build());
    }

}
