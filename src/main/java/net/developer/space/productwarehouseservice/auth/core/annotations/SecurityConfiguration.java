package net.developer.space.productwarehouseservice.auth.core.annotations;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Documented
@Configuration
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityConfiguration {
}
