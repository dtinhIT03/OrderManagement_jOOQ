package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.User;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> findAllById(List<Long> id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }


}
