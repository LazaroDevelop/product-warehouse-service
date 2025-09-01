package net.developer.space.productwarehouseservice.warehouse.domain.repository;

import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {
    Product create(Product product);
    List<Product> findAll();
    List<Product> findByBatch(String batch);
    List<Product> findByFragile(boolean fragile);
    List<Product> findByColor(String color);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    List<Product> findByPackaging(String packaging);
}