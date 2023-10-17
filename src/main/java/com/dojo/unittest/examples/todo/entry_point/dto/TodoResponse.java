package com.dojo.unittest.examples.todo.entry_point.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class TodoResponse {

    Long id;
    String name;
    boolean active;
    String priority;
}
