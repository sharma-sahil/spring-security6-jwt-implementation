package com.nagarro.hackathon.journeyWatch.mapper;

import com.nagarro.hackathon.journeyWatch.dto.UserResponse;
import com.nagarro.hackathon.journeyWatch.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse convertToDto(UserEntity userEntity);
}
