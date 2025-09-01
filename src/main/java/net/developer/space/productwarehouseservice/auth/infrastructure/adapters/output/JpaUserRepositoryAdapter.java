package net.developer.space.productwarehouseservice.auth.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import net.developer.space.productwarehouseservice.auth.domain.model.User;
import net.developer.space.productwarehouseservice.auth.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JpaUserRepositoryAdapter implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(final User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}