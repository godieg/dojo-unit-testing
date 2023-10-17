package com.dojo.unittest.examples.todo.driver_adapter.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("todos")
public class TodoData {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private boolean active;

    @Column
    private String priority;

    @Version
    private Long version;

}
