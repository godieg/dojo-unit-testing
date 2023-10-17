package com.dojo.unittest.examples.todo.config;

import com.dojo.unittest.examples.todo.domain.TodoRepository;
import com.dojo.unittest.examples.todo.domain.TodoUseCase;
import com.dojo.unittest.examples.todo.driven_adapter.TodoAdapter;
import com.dojo.unittest.examples.todo.driven_adapter.TodoReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfiguration {

    @Bean
    public TodoUseCase todoUseCase(TodoRepository todoRepository) {
        return new TodoUseCase(todoRepository);
    }

    @Bean
    public TodoRepository todoRepository(TodoReactiveRepository todoReactiveRepository) {
        return new TodoAdapter(todoReactiveRepository);
    }

}
