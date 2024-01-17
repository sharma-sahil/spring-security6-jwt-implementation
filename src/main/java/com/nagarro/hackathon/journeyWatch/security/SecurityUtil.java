package com.nagarro.hackathon.journeyWatch.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static String getUserName() {
    UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal.getUsername();
  }

}
