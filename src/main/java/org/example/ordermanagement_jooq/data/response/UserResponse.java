package org.example.ordermanagement_jooq.data.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String fullName;
    String address;
    String phone;
}
