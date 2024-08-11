package com.depp.restaurant.service;

import com.depp.restaurant.dtos.SignupRequest;
import com.depp.restaurant.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
