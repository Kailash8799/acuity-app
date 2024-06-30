package com.kailash.acuity.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginDTO(
  @Size(min = 3, message = "Username or email must be at least 3 characters")
  @NotEmpty(message = "Username or email is required")
  String usernameOrEmail,
  @NotEmpty(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  String password
) {}
