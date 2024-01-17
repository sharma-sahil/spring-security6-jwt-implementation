package com.nagarro.hackathon.journeyWatch.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class UserController {

  @GetMapping("/user/userProfile")
  public String userProfile() {
    return "Welcome to User Profile";
  }

  @GetMapping("/user/details")
  public String adminProfile() {
    return "Welcome to Admin Profile";
  }


}
