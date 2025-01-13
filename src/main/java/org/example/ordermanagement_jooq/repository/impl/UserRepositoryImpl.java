package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.Tables;
import generated_sources.tables.pojos.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static generated_sources.tables.User.USER;


@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    DSLContext dslContext;
    @Override
    public List<User> findAllById(List<Long> id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return dslContext.selectFrom(USER).where(USER.ID.equal(id)).fetchOne().into(User.class);
    }

    @Override
    public User findByMail(String mail) {
        return dslContext.selectFrom(USER).where(USER.MAIL.eq(mail)).fetchOne().into(User.class);
    }

    @Override
    public List<User> findAllByListId(List<Long> userIds) {
        return dslContext.selectFrom(USER)
                .where(USER.ID.in(userIds))
                .fetchInto(User.class);
    }


}
