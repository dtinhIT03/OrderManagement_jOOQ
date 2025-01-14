package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.User;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T14:24:09+0700",
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
}
