package com.dojo.unittest.exercises.exc2.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {

    String id;
    String name;
    String email;

}
