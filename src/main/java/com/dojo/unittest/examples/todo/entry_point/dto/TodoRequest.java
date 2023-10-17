package com.dojo.unittest.examples.todo.entry_point.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder(toBuilder = true)
public class TodoRequest {

    String name;
    int active;
    String priority;

}
