package com.nagarro.hackathon.journeyWatch.dto;

import lombok.Data;

public class AuthResponse {

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
