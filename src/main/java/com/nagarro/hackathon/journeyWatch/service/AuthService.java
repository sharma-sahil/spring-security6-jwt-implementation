package com.nagarro.hackathon.journeyWatch.service;

import com.nagarro.hackathon.journeyWatch.dto.AddUserRequest;
import com.nagarro.hackathon.journeyWatch.dto.AuthRequest;
import com.nagarro.hackathon.journeyWatch.dto.AuthResponse;
import com.nagarro.hackathon.journeyWatch.dto.UserResponse;
import com.nagarro.hackathon.journeyWatch.entity.UserEntity;
import com.nagarro.hackathon.journeyWatch.exception.CustomException;
import com.nagarro.hackathon.journeyWatch.mapper.UserMapper;
import com.nagarro.hackathon.journeyWatch.repository.UserRepository;
import com.nagarro.hackathon.journeyWatch.security.JwtTokenUtil;
import com.nagarro.hackathon.journeyWatch.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper userMapper;

  public AuthResponse loginUser(AuthRequest loginRequest) {

    UserEntity userEntity = this.userRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(() -> new CustomException("Invalid user credentials passed in the request. " +
            "Please check credentials", "invalid.credentials"));

    if (!this.passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
      throw new CustomException(
          "Invalid user credentials passed in the request. Please check credentials",
          "invalid.credentials");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

    String token = jwtTokenUtil.generateToken(userDetails);

    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    return authResponse;
  }

  public UserResponse addUser(AddUserRequest addUserRequest) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(addUserRequest.getUsername());
    userEntity.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
    userEntity.setFirstName(addUserRequest.getFirstName());
    userEntity.setLastName(addUserRequest.getLastName());
    userEntity = userRepository.save(userEntity);

    return userMapper.convertToDto(userEntity);
  }

}
