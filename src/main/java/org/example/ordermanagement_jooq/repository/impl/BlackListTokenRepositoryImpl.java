package org.example.ordermanagement_jooq.repository.impl;

import generated_sources.tables.pojos.BlackListToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.repository.BlackListTokenRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static generated_sources.Tables.BLACK_LIST_TOKEN;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class BlackListTokenRepositoryImpl implements BlackListTokenRepository {
    DSLContext dslContext;

    @Override
    public Void save(BlackListToken blackListToken) {
        dslContext.insertInto(BLACK_LIST_TOKEN)
                .set(BLACK_LIST_TOKEN.ID,blackListToken.getId())
                .set(BLACK_LIST_TOKEN.EXPIRYTIME,blackListToken.getExpirytime()).execute();
        return null;
    }
}
