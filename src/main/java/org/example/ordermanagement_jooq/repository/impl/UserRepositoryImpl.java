package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.Tables;
import generated_sources.tables.pojos.User;
import generated_sources.tables.records.UserRecord;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static generated_sources.tables.User.USER;


@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    DSLContext dslContext;


    @Override
    public Optional<User> findById(Long id) {
        return dslContext.selectFrom(USER).where(USER.ID.equal(id)).fetchOptionalInto(User.class);
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return dslContext.selectFrom(USER).where(USER.MAIL.eq(mail)).fetchOptionalInto(User.class);
    }

    @Override
    public List<User> findAllByListId(List<Long> userIds) {
        return dslContext.selectFrom(USER)
                .where(USER.ID.in(userIds))
                .fetchInto(User.class);
    }

    @Override
    public User save(User user) {
        UserRecord userRecord = dslContext.newRecord(USER,user);
        userRecord.store();
        return userRecord.into(User.class);
    }

    @Override
    public Boolean delete(Long id) {
        int rowsAffected =  dslContext.delete(USER).where(USER.ID.eq(id)).execute();
        return rowsAffected > 0;
    }

    @Override
    public Boolean exitsById(Long id) {
        return dslContext.fetchExists(
                dslContext.selectFrom(USER).where(USER.ID.eq(id)));
    }

    @Override
    public Boolean exitsByMail(String mail) {
        return dslContext.fetchExists(dslContext.selectFrom(USER).where(USER.MAIL.eq(mail)));
    }


}
