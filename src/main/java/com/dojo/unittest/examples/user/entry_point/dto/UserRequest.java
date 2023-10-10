package com.dojo.unittest.examples.user.entry_point.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder(toBuilder = true)
public class UserRequest {

    String firstName;
    String lastName;
    String email;
}
