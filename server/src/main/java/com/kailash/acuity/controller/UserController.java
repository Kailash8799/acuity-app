package com.kailash.acuity.controller;

import com.kailash.acuity.service.KafkaProducerService;
import com.kailash.acuity.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final KafkaProducerService kafkaProducerService;

  public UserController(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<ApiResponse<Object>> getAllUsers() {
    for (int i = 0; i < 100; i++) {
      kafkaProducerService.sendMessage(
        "test",
        "Get all users request " + (i + 1)
      );
    }
    ApiResponse<Object> response = new ApiResponse<Object>(
      200,
      "User login successful",
      true,
      null
    );
    return ResponseEntity.ok(response);
  }
}
