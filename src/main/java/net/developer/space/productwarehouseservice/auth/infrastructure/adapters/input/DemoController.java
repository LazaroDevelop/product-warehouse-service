package net.developer.space.productwarehouseservice.auth.infrastructure.adapters.input;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.auth.core.annotations.OutputAdapter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@OutputAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> demo() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject()
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file")MultipartFile file
            ){
        log.info("File name: {}", file.getOriginalFilename());
        return ResponseEntity.ok("File uploaded successfully");
    }

}
