package org.orak.ecommers.response;

import lombok.*;
import org.orak.ecommers.dto.UserDto;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String password;

}
