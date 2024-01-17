package com.nagarro.hackathon.journeyWatch.service;

import com.nagarro.hackathon.journeyWatch.dto.UserResponse;
import com.nagarro.hackathon.journeyWatch.entity.UserEntity;
import com.nagarro.hackathon.journeyWatch.exception.CustomException;
import com.nagarro.hackathon.journeyWatch.mapper.UserMapper;
import com.nagarro.hackathon.journeyWatch.repository.UserRepository;
import com.nagarro.hackathon.journeyWatch.security.SecurityUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public UserResponse getLoggedInUser() {
    String username = SecurityUtil.getUserName();
    Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);
    if (userEntity.isPresent()) {
      return this.userMapper.convertToDto(userEntity.get());
    }

    throw new CustomException("Logged In user details not found");
  }

}
