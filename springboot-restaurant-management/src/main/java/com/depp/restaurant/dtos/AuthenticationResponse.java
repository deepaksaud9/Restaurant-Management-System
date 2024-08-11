package com.depp.restaurant.dtos;

import com.depp.restaurant.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;

}
