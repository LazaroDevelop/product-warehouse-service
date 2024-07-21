package net.developer.space.productwarehouseservice.auth.domain.repository;

import net.developer.space.productwarehouseservice.auth.domain.model.User;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputPort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@OutputPort
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
