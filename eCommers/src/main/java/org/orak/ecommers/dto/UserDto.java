package org.orak.ecommers.dto;

import lombok.*;
import org.orak.ecommers.request.BasketRequest;
import org.orak.ecommers.request.UserRequest;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;


}
