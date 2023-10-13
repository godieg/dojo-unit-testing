package com.dojo.unittest.examples.user.config;

import com.dojo.unittest.examples.user.domain.UserRepository;
import com.dojo.unittest.examples.user.domain.UserUseCase;
import com.dojo.unittest.examples.user.driver_adapter.UserAdapter;
import com.dojo.unittest.examples.user.driver_adapter.UserReactiveRepository;
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

    /*
    @Bean
    public ConnectionPool getConnectionConfig(ConnectionProperties properties) {
        PostgresqlConnectionConfiguration dbConfiguration = PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .database("unittest")
                .schema("public")
                .username("unittest")
                .password("secret")
                .build();

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
                .connectionFactory(new PostgresqlConnectionFactory(dbConfiguration))
                .name("api-postgres-connection-pool")
                .initialSize(12)
                .maxSize(15)
                .maxIdleTime(Duration.ofMinutes(30))
                .validationQuery("SELECT 1")
                .build();

        return new ConnectionPool(poolConfiguration);
    }
     */

}
