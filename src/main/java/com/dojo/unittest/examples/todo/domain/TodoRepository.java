package com.dojo.unittest.examples.todo.domain;

import com.dojo.unittest.examples.todo.domain.model.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoRepository {

    Mono<Todo> save(Todo todo);

    Flux<Todo> findAll();

}
