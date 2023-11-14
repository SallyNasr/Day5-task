package com.example.firstproject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.Mapper.UserMapper;
import com.example.firstproject.entities.UserEntity;
import com.example.firstproject.Repositories.UserRepository;
import com.example.firstproject.model.UserDTO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//This UserService class provides methods for:
//getAllUsers: Retrieve a list of all users.
//getUserById: Retrieve a user by ID.
//createUser: Create a new user.
//updateUser: Update an existing user.
//deleteUser: Delete a user by ID.
@Service
public class UserService {
    //Constructor-based Dependency Injection to inject the UserRepository and UserMapper dependencies.
    private final UserRepository userRepository;//userService has dependency on the userRepository
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userEntityToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity != null)
            return UserMapper.INSTANCE.userEntityToUserDTO(userEntity);
        return null;
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.userDTOToUserEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userMapper.userEntityToUserDTO(userEntity);
    }

//    public UserDTO updateUser(long userId, UserDTO userDTO) {
//        UserEntity existingUserEntity = userRepository.findById(userId).orElse(null);
//
//        if (existingUserEntity != null) {
//            // Update the existing user entity with new data
//            existingUserEntity.setName(userDTO.getName());
//            existingUserEntity.setAge(userDTO.getAge());
//            existingUserEntity.setDepartment(userDTO.getDepartment());
//
//            // Save and return the updated user
//            existingUserEntity = userRepository.save(existingUserEntity);
//            return userMapper.userEntityToUserDTO(existingUserEntity);
//        } else {
//            // Handle the case where the user to update is not found
//            return null;
//        }
//    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }


    public UserDTO patchUser(long userId, UserDTO partialUserDTO) {
        UserEntity existingUserEntity = userRepository.findById(userId).orElse(null);

        if (existingUserEntity != null) {
            // Apply partial updates to the existing user entity
            if (partialUserDTO.getName() != null) {
                existingUserEntity.setName(partialUserDTO.getName());
            }
            if (partialUserDTO.getAge() != 0) {
                existingUserEntity.setAge(partialUserDTO.getAge());
            }
            if (partialUserDTO.getDepartment() != null) {
                existingUserEntity.setDepartment(partialUserDTO.getDepartment());
            }
            // Save and return the updated user
            existingUserEntity = userRepository.save(existingUserEntity);
            return userMapper.userEntityToUserDTO(existingUserEntity);
        } else {
            // Handle the case where the user to update is not found
            return null;
        }
    }
}

//Usage of UserMapper:
//The UserMapper instance (userMapper) is used to perform mappings between UserEntity and UserDTO.