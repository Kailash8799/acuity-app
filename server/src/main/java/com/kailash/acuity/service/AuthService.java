package com.kailash.acuity.service;

import com.kailash.acuity.dto.auth.LoginDTO;
import com.kailash.acuity.dto.auth.SignupDTO;

public interface AuthService {
  public String signin(LoginDTO loginDTO);

  public void signup(SignupDTO signupDTO);
}
