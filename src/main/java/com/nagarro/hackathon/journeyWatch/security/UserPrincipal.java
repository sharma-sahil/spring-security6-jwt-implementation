package com.nagarro.hackathon.journeyWatch.security;

import com.nagarro.hackathon.journeyWatch.entity.UserEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User {

  static final long serialVersionUID = 3964457413286480329L;

  private String username;

  private String firstName;

  private String lastName;

  public UserPrincipal(UserEntity userEnt, List<SimpleGrantedAuthority> authorities) {
    this(userEnt.getUsername(), "", authorities);
    this.firstName = userEnt.getFirstName();
    this.lastName = userEnt.getLastName();
    this.username = userEnt.getUsername();
  }

  public UserPrincipal(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
