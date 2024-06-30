package com.kailash.acuity.service.Impl;

import com.kailash.acuity.dto.user.UserDTO;
import com.kailash.acuity.exception.BadRequestException;
import com.kailash.acuity.exception.ResourceNotFoundException;
import com.kailash.acuity.mapper.UserMapper;
import com.kailash.acuity.model.User;
import com.kailash.acuity.repository.UserRepository;
import com.kailash.acuity.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  UserRepository userRepository;
  UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
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
