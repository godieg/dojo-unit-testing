package com.dojo.unittest.examples.user.entry_point;

import com.dojo.unittest.examples.user.entry_point.dto.UserRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return route(POST("/api/v1/users"),
                userRequest -> userRequest
                        .bodyToMono(UserRequest.class)
                        .flatMap(userHandler::save));
    }

}
