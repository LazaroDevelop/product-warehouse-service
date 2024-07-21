package net.developer.space.productwarehouseservice.auth.infrastructure.config;

import lombok.RequiredArgsConstructor;
import net.developer.space.productwarehouseservice.auth.core.annotations.UserDetailsConfig;
import net.developer.space.productwarehouseservice.auth.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@UserDetailsConfig
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationConfig {

    private final IUserRepository outputPort;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> outputPort.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
