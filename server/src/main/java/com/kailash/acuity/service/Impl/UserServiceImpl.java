package com.kailash.acuity.service.impl;

import com.kailash.acuity.dto.user.UserDTO;
import com.kailash.acuity.exception.BadRequestException;
import com.kailash.acuity.exception.ResourceNotFoundException;
import com.kailash.acuity.mapper.UserMapper;
import com.kailash.acuity.model.User;
import com.kailash.acuity.repository.UserRepository;
import com.kailash.acuity.service.RedisService;
import com.kailash.acuity.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  UserRepository userRepository;
  UserMapper userMapper;
  RedisService redisService;

  public UserServiceImpl(
    UserRepository userRepository,
    UserMapper userMapper,
    RedisService redisService
  ) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.redisService = redisService;
  }

  @Override
  public UserDTO getUserByUsernameOrEmail(String usernameOrEmail) {
    Optional<User> user = userRepository.findByUsernameOrEmail(
      usernameOrEmail,
      usernameOrEmail
    );
    if (user.isPresent()) {
      return userMapper.toUserDTO(user.get());
    }
    return null;
  }

  @Override
  public User getUserForAuthByUsernameOrEmail(String usernameOrEmail) {
    User user = userRepository
      .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
      .orElseThrow(() -> new BadRequestException("Invalid credentials"));
    return user;
  }

  @Override
  public void addRoleToUser(String usernameOrEmail, String role) {
    User user = userRepository
      .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.addRole(role);
    userRepository.save(user);
  }

  @Override
  public void removeRoleFromUser(String usernameOrEmail, String role) {
    User user = userRepository
      .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.removeRole(role);
    userRepository.save(user);
  }
}
