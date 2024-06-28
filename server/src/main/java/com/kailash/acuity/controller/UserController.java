package com.kailash.acuity.controller;

import com.kailash.acuity.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse<Object>> getAllUsers() {
    ApiResponse<Object> response = new ApiResponse<Object>(
      200,
      "User login successful",
      true,
      null
    );
    return ResponseEntity.ok(response);
  }
}
