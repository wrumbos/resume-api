package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.request.LoginRequest;
import com.wcode.resume.model.request.SignupRequest;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.AuthServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.wcode.resume.constant.ConferenceConstant.*;

import javax.validation.Valid;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Authorization Services")
public class AuthController {

    private AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @PostMapping("/signup")
    @ApiOperation(value = "signup: Creates new User")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        logger.info(LOG_CREATE_USER);
        return authService.registerUser(signUpRequest)
                .map(user -> ResponseEntity.ok(new ApiResponse(true, user.toString())))
                .orElseThrow(() -> new ApiRequestException(ERROR_CREATE_USER));

    }

    @PostMapping("/signin")
    @ApiOperation(value = "signin: Login user")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info(LOG_LOGIN_USER);
        return authService.authenticateUser(loginRequest)
                .map(loginResponse -> ResponseEntity.ok(loginResponse))
                .orElseThrow(() -> new ApiRequestException(ERROR_LOGIN_USER));

    }

}
