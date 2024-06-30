package com.kailash.acuity.service;

import com.kailash.acuity.dto.user.UserDTO;
import com.kailash.acuity.model.User;

public interface UserService {
  User getUserForAuthByUsernameOrEmail(String usernameOrEmail);
  UserDTO getUserByUsernameOrEmail(String usernameOrEmail);
  void addRoleToUser(String usernameOrEmail, String role);
  void removeRoleFromUser(String usernameOrEmail, String role);
}
