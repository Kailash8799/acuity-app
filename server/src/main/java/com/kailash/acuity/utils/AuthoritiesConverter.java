package com.kailash.acuity.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class AuthoritiesConverter implements AttributeConverter<Set<GrantedAuthority>, String> {

  @Override
  public String convertToDatabaseColumn(Set<GrantedAuthority> authorities) {
    return authorities != null ? authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(",")) : "";
  }

  @Override
  public Set<GrantedAuthority> convertToEntityAttribute(String dbData) {
    return dbData != null && !dbData.isEmpty() ? Arrays.stream(dbData.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet()) : Collections.emptySet();
  }
}
