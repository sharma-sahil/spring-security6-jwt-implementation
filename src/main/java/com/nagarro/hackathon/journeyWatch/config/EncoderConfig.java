package com.nagarro.hackathon.journeyWatch.config;

import org.springframework.context.annotation.Configuration;

/**
 * Normally this bean can be created within {@link SecurityConfig} class only, but since the release
 * of spring boot 2.6 version, creating the bean in {@link SecurityConfig} will give an exception of
 * circular dependency and application will fail to start. So this separate config class is required
 * to break the circular dependency exception.
 */
@Configuration
public class EncoderConfig {
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
}