package net.developer.space.productwarehouseservice.auth.core.annotations;

import java.lang.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@Documented
@RestController
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputAdapter {}
