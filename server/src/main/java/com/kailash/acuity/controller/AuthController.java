package com.kailash.acuity.controller;

import com.kailash.acuity.dto.auth.LoginDTO;
import com.kailash.acuity.dto.auth.SignupDTO;
import com.kailash.acuity.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @PostMapping("signin")
  public ResponseEntity<ApiResponse<Object>> loginUser(
    @Valid @RequestBody LoginDTO loginDTO
  ) {
    ApiResponse<Object> response = new ApiResponse<Object>(
      200,
      "User login successful",
      true,
      null
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("signup")
  public ResponseEntity<ApiResponse<Object>> createUser(
    @Valid @RequestBody SignupDTO signupDTO
  ) {
    ApiResponse<Object> response = new ApiResponse<Object>(
      201,
      "User created successfully",
      true,
      null
    );
    return ResponseEntity.status(201).body(response);
  }
}
