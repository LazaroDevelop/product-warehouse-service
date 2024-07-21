package net.developer.space.productwarehouseservice.auth.core.annotations;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtAuthFilter {
}
