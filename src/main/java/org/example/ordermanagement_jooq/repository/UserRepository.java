package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.User;
import org.example.ordermanagement_jooq.data.response.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    List<User> findAllById(List<Long> id);
    User findById(Long id);
    User findByMail(String mail);

    List<User> findAllByListId(List<Long> userIds);
}
