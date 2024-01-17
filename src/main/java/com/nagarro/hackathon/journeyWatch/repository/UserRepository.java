package com.nagarro.hackathon.journeyWatch.repository;

import com.nagarro.hackathon.journeyWatch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}