package com.wcode.resume.service;

import com.wcode.resume.model.data.User;
import com.wcode.resume.model.request.LoginRequest;
import com.wcode.resume.model.request.SignupRequest;
import com.wcode.resume.model.response.LoginResponse;

import java.util.Optional;

public interface AuthService {
    Optional<User> registerUser(SignupRequest signupRequest);
    Optional<LoginResponse> authenticateUser(LoginRequest loginRequest);
}
