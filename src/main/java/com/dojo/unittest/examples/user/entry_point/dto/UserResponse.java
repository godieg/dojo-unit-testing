package com.dojo.unittest.examples.user.entry_point.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class UserResponse {

    Long id;
    String firstName;
    String lastName;
    String email;

}
