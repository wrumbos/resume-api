package com.wcode.resume.serviceImpl;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Role;
import com.wcode.resume.model.data.Roles;
import com.wcode.resume.model.data.User;
import com.wcode.resume.model.request.LoginRequest;
import com.wcode.resume.model.request.SignupRequest;
import com.wcode.resume.model.response.LoginResponse;
import com.wcode.resume.model.security.UserDetailsImpl;
import com.wcode.resume.repository.RoleRepository;
import com.wcode.resume.repository.UserRepository;
import com.wcode.resume.service.AuthService;
import com.wcode.resume.utils.JsonWebTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    private AuthenticationManager authenticationManager;
    private JsonWebTokenUtils jsonWebTokenUtils;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder encoder, AuthenticationManager authenticationManager,
                           JsonWebTokenUtils jsonWebTokenUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jsonWebTokenUtils = jsonWebTokenUtils;
    }

    @Override
    public Optional<User> registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())
                || userRepository.existsByEmail(signupRequest.getEmail())) {
            logger.error("El nombre de usuario o el correo ya se encuentran registrados: " +
                    "[" + signupRequest.getUsername() + "-" + signupRequest.getEmail() +"]");
            throw new ApiRequestException("El nombre de usuario o el correo ya se encuentran registrados");
        }

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                .orElseThrow(() -> new ApiRequestException("Error: Role is not found."));
        roles.add(userRole);

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                roles);


        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<LoginResponse> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jsonWebToken = jsonWebTokenUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return Optional.ofNullable(new LoginResponse(jsonWebToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
