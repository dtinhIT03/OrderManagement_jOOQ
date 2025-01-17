package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.User;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.request.AuthenticationRequest;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-15T10:16:20+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserReponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setFullname( user.getFullname() );
        userResponse.setAddress( user.getAddress() );
        userResponse.setPhone( user.getPhone() );

        return userResponse;
    }

    @Override
    public User toUser(AuthenticationRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setFullname( request.getFullname() );
        user.setPassword( request.getPassword() );
        user.setMail( request.getMail() );

        return user;
    }
}
