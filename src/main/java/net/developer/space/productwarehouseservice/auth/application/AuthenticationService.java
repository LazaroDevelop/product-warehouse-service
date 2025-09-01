package net.developer.space.productwarehouseservice.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthenticationResponse;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthorizationRequest;
import net.developer.space.productwarehouseservice.auth.application.dtos.RegisterRequest;
import net.developer.space.productwarehouseservice.auth.application.ports.input.IAuthenticationService;
import net.developer.space.productwarehouseservice.auth.domain.model.Role;
import net.developer.space.productwarehouseservice.auth.domain.model.User;
import net.developer.space.productwarehouseservice.auth.domain.repository.IUserRepository;
import net.developer.space.productwarehouseservice.auth.infrastructure.config.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements IAuthenticationService {

    private final JwtGenerator generator;

    private final IUserRepository userAdapter;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private static final String BEARER = "Bearer ";

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){

        var user = User.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userAdapter.save(user);

        var jwtToken = generator.generateToken(user);

        return AuthenticationResponse.builder()
                .tokenType(BEARER)
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthorizationRequest authorizationRequest){

        log.debug("passowrd {}", authorizationRequest.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authorizationRequest.getUsername(),
                authorizationRequest.getPassword()
        ));

        var user = userAdapter.findByEmail(authorizationRequest.getUsername());

        var jwtToken = generator.generateToken(user);

        return AuthenticationResponse.builder()
                .tokenType(BEARER)
                .accessToken(jwtToken)
                .build();
    }

}
