package net.developer.space.productwarehouseservice.auth.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthenticationResponse;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthorizationRequest;
import net.developer.space.productwarehouseservice.auth.application.dtos.RegisterRequest;
import net.developer.space.productwarehouseservice.auth.application.ports.input.IAuthenticationService;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputAdapter;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputPort;
import net.developer.space.productwarehouseservice.auth.domain.model.Role;
import net.developer.space.productwarehouseservice.auth.domain.model.User;
import net.developer.space.productwarehouseservice.auth.infrastructure.config.JwtGenerator;
import net.developer.space.productwarehouseservice.auth.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements IAuthenticationService {

    private final JwtGenerator generator;

    private final IUserRepository outputPort;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        outputPort.save(user);

        var jwtToken = generator.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthorizationRequest authorizationRequest){

        log.info("passowrd {}", authorizationRequest.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authorizationRequest.getUserName(),
                authorizationRequest.getPassword()
        ));

        var user = outputPort.findByEmail(authorizationRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = generator.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
