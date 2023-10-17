package com.dojo.unittest.examples.todo.driver_adapter;

import com.dojo.unittest.examples.todo.driver_adapter.model.TodoData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TodoReactiveRepository extends ReactiveCrudRepository<TodoData, Long> {
}
