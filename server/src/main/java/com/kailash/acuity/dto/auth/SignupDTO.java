package com.kailash.acuity.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record SignupDTO(
  @NotEmpty(message = "Username is required")
  @Size(min = 3, message = "Username must be at least 3 characters")
  String username,
  @Email(message = "Email is not valid")
  @NotEmpty(message = "Email is required")
  String email,
  @NotEmpty(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  String password
) {}
