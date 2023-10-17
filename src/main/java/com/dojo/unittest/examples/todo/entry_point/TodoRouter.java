package com.dojo.unittest.examples.todo.entry_point;

import com.dojo.unittest.examples.todo.entry_point.dto.TodoRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TodoRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(TodoHandler todoHandler) {
        return route(POST("/api/v1/todos"),
                userRequest -> userRequest
                        .bodyToMono(TodoRequest.class)
                        .flatMap(todoHandler::save))
                .and(route(GET("/api/v1/todos"), todoHandler::findAll));
    }

}
