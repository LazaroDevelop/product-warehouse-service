package net.developer.space.productwarehouseservice.auth.infrastructure.adapters.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthenticationResponse;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthorizationRequest;
import net.developer.space.productwarehouseservice.auth.application.dtos.RegisterRequest;
import net.developer.space.productwarehouseservice.auth.application.ports.input.IAuthenticationService;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@OutputAdapter
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthorizationRequest login){
        return ResponseEntity.ok(authenticationService.authenticate(login));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest user){
        return ResponseEntity.ok(authenticationService.register(user));
    }

}
