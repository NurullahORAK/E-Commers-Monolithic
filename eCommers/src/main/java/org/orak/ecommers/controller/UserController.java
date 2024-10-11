package org.orak.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.UserDto;
import org.orak.ecommers.request.UserRequest;
import org.orak.ecommers.response.UserResponse;
import org.orak.ecommers.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponse get(@RequestParam(value = "id") String id) {
        return toResponse(userService.get(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        userService.delete(id);
    }

    @PostMapping
    public UserResponse cretae(@RequestBody UserRequest userRequest) {
        return toResponse(userService.create(toDto(userRequest)));
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable(name = "id") String id, @RequestBody UserRequest userRequest) {
        return toResponse(userService.update(id, toDto(userRequest)));
    }

    private UserDto toDto(UserRequest userRequest) {
        return UserDto.builder().
                name(userRequest.name).
                email(userRequest.email)
                .password(userRequest.password).build();
    }
    public UserResponse toResponse(UserDto userDto) {
        return UserResponse.builder().
                id(userDto.getId()).
                email(userDto.getEmail()).
                name(userDto.getName()).
                password(userDto.getPassword()).build();
    }
}