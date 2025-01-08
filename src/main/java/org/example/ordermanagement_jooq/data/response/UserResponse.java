package org.example.ordermanagement_jooq.data.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String fullname;
    String address;
    String phone;
}
