package com.dojo.unittest.examples.todo.entry_point;

import com.dojo.unittest.examples.todo.domain.TodoUseCase;
import com.dojo.unittest.examples.todo.entry_point.dto.TodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TodoHandler {

    private final TodoUseCase todoUseCase;

    public Mono<ServerResponse> save(TodoRequest todoRequest) {
        return Mono.empty();
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return Mono.empty();
    }

}
