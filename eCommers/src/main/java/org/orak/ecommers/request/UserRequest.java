package org.orak.ecommers.request;

import lombok.Builder;

@Builder

public class UserRequest {
    public String name;
    public String email;
    public String password;
}
