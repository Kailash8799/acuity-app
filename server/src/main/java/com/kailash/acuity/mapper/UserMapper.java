package com.kailash.acuity.mapper;

import com.kailash.acuity.dto.auth.SignupDTO;
import com.kailash.acuity.dto.user.UserDTO;
import com.kailash.acuity.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDTO toUserDTO(User user) {
    if (user == null) return null;
    return new UserDTO(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getFirstName(),
      user.getLastName(),
      user.getPhoneNumber(),
      user.getAddress(),
      user.getProfilePictureUrl(),
      user.getAuthorities(),
      user.isEnabled()
    );
  }

  public User toSignupUser(SignupDTO signupDTO) {
    if (signupDTO == null) return null;
    User user = new User();
    user.setUsername(signupDTO.username());
    user.setEmail(signupDTO.email());
    user.setPassword(signupDTO.password());
    return user;
  }
}
