package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.User;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserReponse(User user);

    User toUser(AuthenticationRequest request);
}
