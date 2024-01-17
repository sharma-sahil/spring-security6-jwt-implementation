package com.nagarro.hackathon.journeyWatch.web.rest;

import com.nagarro.hackathon.journeyWatch.dto.AddUserRequest;
import com.nagarro.hackathon.journeyWatch.dto.AuthRequest;
import com.nagarro.hackathon.journeyWatch.dto.AuthResponse;
import com.nagarro.hackathon.journeyWatch.dto.UserResponse;
import com.nagarro.hackathon.journeyWatch.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/addNewUser")
  public UserResponse addNewUser(@RequestBody AddUserRequest userInfo) {
    return authService.addUser(userInfo);
  }

  @PostMapping("/generateToken")
  public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    return this.authService.loginUser(authRequest);
  }

}
