package com.depp.restaurant.controller;

import com.depp.restaurant.Response.ApiResponse;
import com.depp.restaurant.dtos.AuthenticationRequest;
import com.depp.restaurant.dtos.AuthenticationResponse;
import com.depp.restaurant.dtos.SignupRequest;
import com.depp.restaurant.dtos.UserDto;
import com.depp.restaurant.entity.User;
import com.depp.restaurant.repository.UserRepository;
import com.depp.restaurant.service.serviceImpl.AuthService;
import com.depp.restaurant.service.jwt.UserDetailServiceImpl;
import com.depp.restaurant.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailService, UserRepository userRepository, JwtUtils jwtUtils){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
       UserDto createdUser = authService.createUser(signupRequest);
        ApiResponse<UserDto> response = new ApiResponse<>(HttpStatus.CREATED.value(),"User created Successfully", createdUser );
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username or Password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User not active");
            return null;
        }
           final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtils.generateToken(userDetails.getUsername());
            Optional<User>  optionalUser = userRepository.findUserByEmail(userDetails.getUsername());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            if (optionalUser.isPresent()){
                authenticationResponse.setJwt(jwt);
                authenticationResponse.setUserRole(optionalUser.get().getUserRole());
                authenticationResponse.setUserId(optionalUser.get().getId());
            }
            return authenticationResponse;
    }


}
