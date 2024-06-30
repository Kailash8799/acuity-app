package com.kailash.acuity.dto.user;

import java.util.Collection;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;

public record UserDTO(
  UUID id,
  String username,
  String email,
  String firstName,
  String lastName,
  String phoneNumber,
  String address,
  String profilePictureUrl,
  Collection<? extends GrantedAuthority> authorities,
  boolean enabled
) {}
