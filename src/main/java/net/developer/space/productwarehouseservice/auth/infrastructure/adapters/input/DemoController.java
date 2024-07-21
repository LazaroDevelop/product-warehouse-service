package net.developer.space.productwarehouseservice.auth.infrastructure.adapters.input;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@OutputAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello World");
    }

}
