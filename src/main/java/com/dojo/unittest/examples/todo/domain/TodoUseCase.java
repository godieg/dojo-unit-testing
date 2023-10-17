package com.dojo.unittest.examples.todo.domain;

import com.dojo.unittest.examples.todo.domain.model.Todo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TodoUseCase {

    private final TodoRepository todoRepository;

    public Mono<Todo> save(Todo todo) {
        return Mono.empty();
    }

    public Flux<Todo> findAll() {
        return Flux.empty();
    }

}
