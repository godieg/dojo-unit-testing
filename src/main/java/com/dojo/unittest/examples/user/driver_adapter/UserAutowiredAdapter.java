package com.dojo.unittest.examples.user.driver_adapter;

import com.dojo.unittest.examples.user.domain.UserRepository;
import com.dojo.unittest.examples.user.domain.model.User;
import com.dojo.unittest.examples.user.driver_adapter.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("userAutowiredAdapter")
public class UserAutowiredAdapter implements UserRepository {

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    /*
    @Autowired
    public UserAutowiredAdapter(UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    @Autowired
    public void setUserReactiveRepository(UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }
    */

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
