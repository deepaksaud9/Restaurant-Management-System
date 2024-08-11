package com.depp.restaurant.mapper;

import com.depp.restaurant.dtos.SignupRequest;
import com.depp.restaurant.dtos.UserDto;
import com.depp.restaurant.entity.User;
import com.depp.restaurant.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(SignupRequest signupRequest, PasswordEncoder passwordEncoder){

        if (signupRequest == null){
            return null;
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        return user;
    }

    public UserDto toDto(User user, PasswordEncoder passwordEncoder){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}
