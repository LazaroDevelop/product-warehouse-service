package net.developer.space.productwarehouseservice.auth.infrastructure.adapters.output;

import net.developer.space.productwarehouseservice.auth.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    User save(User user);
}