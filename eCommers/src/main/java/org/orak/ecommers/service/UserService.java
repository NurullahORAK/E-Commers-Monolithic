package org.orak.ecommers.service;

import org.orak.ecommers.dto.UserDto;

public interface UserService {  // UserService interface i metod imzasını alır.
    UserDto get(String id);

    void delete(String id);

    UserDto create(UserDto dto);

    UserDto update(String id, UserDto dto);
}
