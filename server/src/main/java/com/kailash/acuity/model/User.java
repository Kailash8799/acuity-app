package com.kailash.acuity.model;

import com.kailash.acuity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String address;
  private String profilePictureUrl;

  private boolean enabled;

  @PrePersist
  protected void onCreate() {
    role = "USER";
    enabled = true;
  }
}
