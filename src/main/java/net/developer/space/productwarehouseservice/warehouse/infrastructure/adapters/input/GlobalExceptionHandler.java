package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.input;

import net.developer.space.productwarehouseservice.warehouse.application.dto.ApiError;
import net.developer.space.productwarehouseservice.warehouse.application.exceptions.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import net.developer.space.productwarehouseservice.warehouse.application.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // First way
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundException(UserNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String badCredentialException(BadCredentialsException ex){
        return ex.getMessage();
    }

    //Second way

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleException(UserNotFoundException e, HttpServletRequest request){
        ApiError apiError = ApiError.builder()
                .statusCode(404)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .issuedAt(java.time.LocalDate.now())
                .build();
        return ResponseEntity.status(404).body(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleException(BadCredentialsException e, HttpServletRequest request){
        ApiError apiError = ApiError.builder()
                .statusCode(401)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .issuedAt(java.time.LocalDate.now())
                .build();
        return ResponseEntity.status(401).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request){
        ApiError apiError = ApiError.builder()
                .statusCode(500)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .issuedAt(java.time.LocalDate.now())
                .build();
        return ResponseEntity.status(500).body(apiError);
    }
}
