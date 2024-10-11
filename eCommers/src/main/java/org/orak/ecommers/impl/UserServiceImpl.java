package org.orak.ecommers.impl;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.UserDto;
import org.orak.ecommers.entity.User;
import org.orak.ecommers.repository.UserRepository;
import org.orak.ecommers.request.UserRequest;
import org.orak.ecommers.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public UserDto get(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public UserDto update(String id, UserDto dto) {
        UserDto dto1 = toDto(repository.findById(Integer.parseInt(id)).get());
        UserDto a = UserDto.builder()
                .email(dto1.getEmail())
                .name(dto1.getName())
                .id(dto1.getId())
                .password(dto1.getPassword()).build();
        return toDto(repository.save(toEntity(a)));
    }

    private User toEntity(UserDto a) {
        return User.builder().
                name(a.getName()).
                email(a.getEmail()).
                id(a.getId()).
                password(a.getPassword()).build();
    }

    @Override
    public UserDto create(UserDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    private UserDto toDto(User user) {
        return UserDto.builder().
                name(user.getName()).
                email(user.getEmail())
                .password(user.getPassword()).
                id(user.getId()).build();
    }

    public User findByUser(int id){
        return userRepository.findById(id).get();
    }
}