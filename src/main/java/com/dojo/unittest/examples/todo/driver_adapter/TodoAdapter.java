package com.dojo.unittest.examples.todo.driver_adapter;

import com.dojo.unittest.examples.todo.domain.TodoRepository;
import com.dojo.unittest.examples.todo.domain.model.Todo;
import com.dojo.unittest.examples.todo.driver_adapter.model.TodoData;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TodoAdapter implements TodoRepository {

    private final TodoReactiveRepository todoReactiveRepository;

    @Override
    public Mono<Todo> save(Todo todo) {
        return null;
    }

    @Override
    public Flux<Todo> findAll() {
        return null;
    }

    // Crear metodo privado para transformar del objeto A al objeto B.
    private TodoData toData(Todo todo) {
        return TodoData
                .builder()
                .build();
    }

}
