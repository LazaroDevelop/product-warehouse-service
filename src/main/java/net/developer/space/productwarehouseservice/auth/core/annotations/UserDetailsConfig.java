package net.developer.space.productwarehouseservice.auth.core.annotations;

import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;


@Documented
@Configuration
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserDetailsConfig {}
