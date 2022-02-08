package com.springboot.users.service;

import com.springboot.users.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    String deleteUser(Long id);

    UserDto createUser(UserDto newUSer);

    UserDto updateUser(UserDto userDto, Long id);

}
