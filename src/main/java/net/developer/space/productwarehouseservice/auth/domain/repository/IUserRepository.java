package net.developer.space.productwarehouseservice.auth.domain.repository;

import net.developer.space.productwarehouseservice.auth.domain.model.User;


public interface IUserRepository{
    User save(User user);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
