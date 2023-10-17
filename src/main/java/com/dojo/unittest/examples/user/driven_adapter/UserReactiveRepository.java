package com.dojo.unittest.examples.user.driven_adapter;

import com.dojo.unittest.examples.user.driven_adapter.model.UserData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserReactiveRepository extends ReactiveCrudRepository<UserData, Long> {
}
