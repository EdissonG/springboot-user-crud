package com.springboot.users.service.impl;

import com.springboot.users.dto.UserDto;
import com.springboot.users.entity.User;
import com.springboot.users.repository.UserRepository;
import com.springboot.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    // Injection de la clase reepositorio para usar con la db
    private final UserRepository repository;
    // inializacion de variables
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = repository.findAll();

        // Mapear Entidad usuario a dto
        List<UserDto> userList = users.stream().map(this::mapToDto).collect(Collectors.toList());

        // retornar lista de usuarios
        return userList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.findById(id).get();

        UserDto userDto =  mapToDto(user);

        return userDto;
    }

    @Override
    public String deleteUser(Long id) {
        // Repositorio todos los metodos a la db
        User user = repository.findById(id).get();

        repository.delete(user);

        return "Se elimino el usuario";

    }

    @Override
    public UserDto createUser(UserDto newUSer) {
        // Mapearlo en una entidad DTO
        User user = mapToEntity(newUSer);
        //Guardar en la base de datos el nuevo usuario
        User nUser = repository.save(user);
        //Retorno del nuevo usuario
        return  mapToDto(nUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        //Consultar usuario por id
        User user = repository.findById(id).get();

        // Colocar nuevos datos
        user.setId(id);
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        // Actualizar usuario
        User updatedUser = repository.save(user);

        // Retorna usuario DTO
        return  mapToDto(updatedUser);

    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }

    private User mapToEntity(UserDto userDto) {
        //Mapeo de entiendad usuario a dto
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getEmail(),
                userDto.getPassword()

        );

        return user;
    }
}
