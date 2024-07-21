package net.developer.space.productwarehouseservice.auth.core.annotations;

import java.lang.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OutputPort {}
