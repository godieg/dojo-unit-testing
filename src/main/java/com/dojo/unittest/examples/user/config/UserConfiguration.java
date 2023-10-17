package com.dojo.unittest.examples.user.config;

import com.dojo.unittest.examples.user.domain.UserRepository;
import com.dojo.unittest.examples.user.domain.UserUseCase;
import com.dojo.unittest.examples.user.driven_adapter.UserAdapter;
import com.dojo.unittest.examples.user.driven_adapter.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserUseCase userUseCase(@Qualifier("userAdapter") UserRepository userRepository) {
        return new UserUseCase(userRepository);
    }

    @Bean("userAdapter")
    public UserRepository userRepository(UserReactiveRepository userReactiveRepository) {
        return new UserAdapter(userReactiveRepository);
    }

}
