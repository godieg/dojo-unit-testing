package com.dojo.unittest.examples.todo.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Priority {

    LOW(1, "L"),
    MEDIUM(2, "M"),
    HIGH(3, "H");

    private final int index;
    private final String code;

}