package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output;

import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductPackaging;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBatch(String batch);
    List<Product> findByFragile(boolean fragile);
    List<Product> findByColor(String color);
    List<Product> findByPriceBetween(BigDecimal lowerPrice, BigDecimal upperPrice);
    List<Product> findByPackaging(ProductPackaging packaging);
}