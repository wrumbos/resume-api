package com.wcode.resume.service;

import com.wcode.resume.model.data.User;
import com.wcode.resume.model.request.SignupRequest;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {
    Optional<User> registerUser(SignupRequest signupRequest);
}
