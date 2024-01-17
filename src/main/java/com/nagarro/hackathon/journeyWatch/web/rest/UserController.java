package com.nagarro.hackathon.journeyWatch.web.rest;

import com.nagarro.hackathon.journeyWatch.dto.UserResponse;
import com.nagarro.hackathon.journeyWatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public UserResponse getUserDetails() {
    return userService.getLoggedInUser();
  }

}
