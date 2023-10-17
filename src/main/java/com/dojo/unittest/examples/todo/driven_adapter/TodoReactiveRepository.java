package com.dojo.unittest.examples.todo.driven_adapter;

import com.dojo.unittest.examples.todo.driven_adapter.model.TodoData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoReactiveRepository extends ReactiveCrudRepository<TodoData, Long> {
}
