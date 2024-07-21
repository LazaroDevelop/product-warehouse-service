package net.developer.space.productwarehouseservice.auth.application.ports.input;

import net.developer.space.productwarehouseservice.auth.application.dtos.AuthenticationResponse;
import net.developer.space.productwarehouseservice.auth.application.dtos.AuthorizationRequest;
import net.developer.space.productwarehouseservice.auth.application.dtos.RegisterRequest;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthorizationRequest authorizationRequest);
}
