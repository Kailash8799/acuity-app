package com.kailash.acuity.model;

import com.kailash.acuity.common.BaseEntity;
import com.kailash.acuity.utils.AuthoritiesConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Convert(converter = AuthoritiesConverter.class)
  private Set<GrantedAuthority> authorities;

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String address;
  private String profilePictureUrl;

  private boolean enabled;

  @PrePersist
  protected void onCreate() {
    enabled = true;
    ensureDefaultRole();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  public void addRole(String role) {
    this.authorities.add(new SimpleGrantedAuthority(role));
    ensureDefaultRole();
  }

  public void removeRole(String role) {
    this.authorities.remove(new SimpleGrantedAuthority(role));
    ensureDefaultRole();
  }

  private void ensureDefaultRole() {
    if (this.authorities == null) {
      this.authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
    } else if (
      !this.authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))
    ) {
      this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }
}
