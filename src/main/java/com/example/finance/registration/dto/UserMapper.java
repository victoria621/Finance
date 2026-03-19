package com.example.finance.registration.dto;

import com.example.finance.registration.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
public UserDto toDto(UserEntity userEntity){
	return new UserDto(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getPassword()
    );
}

public UserEntity toEntity(UserDto userDto){
    return new UserEntity(
            userDto.id(),
            userDto.name(),
            userDto.email(),
            userDto.password()
    );
}

}
