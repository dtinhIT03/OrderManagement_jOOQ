package org.example.ordermanagement_jooq.repository;

import generated_sources.tables.pojos.User;
import org.example.ordermanagement_jooq.data.response.UserResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
    Optional<User> findByMail(String mail);

    List<User> findAllByListId(List<Long> userIds);

    User save(User user);

    Boolean delete(Long id);

    Boolean exitsById(Long id);
}
