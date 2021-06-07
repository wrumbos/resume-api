package com.wcode.resume.controller;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.request.SignupRequest;
import com.wcode.resume.model.response.ApiResponse;
import com.wcode.resume.serviceImpl.AuthServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Authorization Rest API")
public class AuthController {

    private AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "signup: Creates new User")
    public ResponseEntity registerUser(@Valid
                                       @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest)
                .map(user -> ResponseEntity.ok(new ApiResponse(true, user.toString())))
                .orElseThrow(() -> new ApiRequestException("El nombre de usuario o el correo ya se encuentran registrados"));

    }
}
