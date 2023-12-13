package com.dojo.unittest.exercises.exc2.adapter;

import com.dojo.unittest.exercises.exc2.domain.model.User;

import java.util.List;
import java.util.function.BiPredicate;

public class UserGateway {

    private static final List<User> availableUsers = List.of(
            User.builder().id("1").name("User 1").email("user1@example.com").build(),
            User.builder().id("2").name("User 2").email("user2@example.com").build(),
            User.builder().id("3").name("User 3").email("user3@example.com").build(),
            User.builder().id("4").name("User 4").email("user4@example.com").build()
    );

    private final BiPredicate<User, String> existUserByEmail = (user, email) -> user.getEmail().equalsIgnoreCase(email);

    public Boolean exists(String email) {
        return availableUsers
                .stream()
                .anyMatch(user -> this.existUserByEmail.test(user, email));
    }

}
