package com.depp.restaurant.service.auth;

import com.depp.restaurant.dtos.SignupRequest;
import com.depp.restaurant.dtos.UserDto;
import com.depp.restaurant.entity.User;
import com.depp.restaurant.enums.UserRole;
import com.depp.restaurant.mapper.UserMapper;
import com.depp.restaurant.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findUserByUserRole(UserRole.ADMIN);
        if (adminAccount == null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);


        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {

        User savedUser = userRepository.save(userMapper.toEntity(signupRequest,passwordEncoder));
        return userMapper.toDto(savedUser,passwordEncoder);
    }
}
