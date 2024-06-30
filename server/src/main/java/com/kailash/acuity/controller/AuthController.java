package com.kailash.acuity.controller;

import com.kailash.acuity.dto.auth.LoginDTO;
import com.kailash.acuity.dto.auth.SignupDTO;
import com.kailash.acuity.service.AuthService;
import com.kailash.acuity.utils.ApiResponse;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("signin")
  public ResponseEntity<ApiResponse<Object>> loginUser(
    @Valid @RequestBody LoginDTO loginDTO
  ) {
    String token = authService.signin(loginDTO);
    Map<String, String> data = Map.of("token", token);
    ApiResponse<Object> response = new ApiResponse<Object>(
      200,
      "User login successful",
      true,
      data
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("signup")
  public ResponseEntity<ApiResponse<Object>> createUser(
    @Valid @RequestBody SignupDTO signupDTO
  ) {
    authService.signup(signupDTO);
    ApiResponse<Object> response = new ApiResponse<Object>(
      201,
      "User created successfully",
      true,
      null
    );
    return ResponseEntity.status(201).body(response);
  }
}
