package com.kailash.acuity.service.impl;

import com.kailash.acuity.dto.auth.LoginDTO;
import com.kailash.acuity.dto.auth.SignupDTO;
import com.kailash.acuity.mapper.UserMapper;
import com.kailash.acuity.model.User;
import com.kailash.acuity.repository.UserRepository;
import com.kailash.acuity.service.AuthService;
import com.kailash.acuity.utils.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private UserRepository userRepository;
  private UserMapper userMapper;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private JwtUtils jwtUtils;

  public AuthServiceImpl(
    UserRepository userRepository,
    UserMapper userMapper,
    PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager,
    JwtUtils jwtUtils
  ) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public String signin(LoginDTO loginDTO) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginDTO.usernameOrEmail(),
        loginDTO.password()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user = (User) authentication.getPrincipal();
    String token = jwtUtils.generateTokenFromUser(user);
    return token;
  }

  @Override
  @Transactional
  public void signup(SignupDTO signupDTO) {
    User user = userMapper.toSignupUser(signupDTO);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.saveAndFlush(user);
  }
}
