package com.wcode.resume.serviceImpl;

import com.wcode.resume.exception.ApiRequestException;
import com.wcode.resume.model.data.Role;
import com.wcode.resume.model.data.Roles;
import com.wcode.resume.model.data.User;
import com.wcode.resume.model.request.SignupRequest;
import com.wcode.resume.repository.RoleRepository;
import com.wcode.resume.repository.UserRepository;
import com.wcode.resume.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
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
}
