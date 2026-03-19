package com.example.finance.service;

import com.example.finance.dto.UserDto;
import com.example.finance.dto.UserMapper;
import com.example.finance.entity.UserEntity;
import com.example.finance.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

     public List<UserDto> getAllUsers() {
         List<UserEntity> allEntities = userRepository.findAll();
         return allEntities.stream()
                 .map(userMapper::toDto)
                 .toList();
     }

     public UserDto getUserById(Long id) {
        UserEntity entities = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found")
        );

        return userMapper.toDto(entities);
     }

     @Transactional
     public UserDto createdUser(
             UserDto createdUser
     ) {
       var entityToSave = new UserEntity(
          createdUser.id(),
          createdUser.name(),
          createdUser.email(),
          createdUser.password()
        );
        userRepository.save(entityToSave);
        return userMapper.toDto(entityToSave);
     }

     @Transactional
     public UserDto updateUser(
             Long id,
             UserDto updatedUser
     ){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));

        userEntity.setUsername(updatedUser.name());
        userEntity.setEmail(updatedUser.email());
        userEntity.setPassword(updatedUser.password());

        return userMapper.toDto(userRepository.save(userEntity));
     }

     @Transactional
     public void deleteUser(
             Long id
     ) {
        UserEntity userEntity =userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " not found"));

        userRepository.delete(userEntity);
     }
}
