package net.developer.space.productwarehouseservice.warehouse.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private int statusCode;
    private String message;
    private String path;
    private LocalDate issuedAt;
}
