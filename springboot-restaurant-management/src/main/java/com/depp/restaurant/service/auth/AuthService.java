package com.depp.restaurant.service.auth;

import com.depp.restaurant.dtos.SignupRequest;
import com.depp.restaurant.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
