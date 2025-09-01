package net.developer.space.productwarehouseservice.warehouse.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    public int productNumber;
    private float size;
    private String name;
    private String batch;
    private String color;
    private boolean fragile;
    private BigDecimal price;
    private String packaging;
}
