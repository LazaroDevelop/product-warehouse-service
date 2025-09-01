package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductPackaging;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product create(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return this.jpaProductRepository.findAll();
    }

    @Override
    public List<Product> findByBatch(String batch) {
        return this.jpaProductRepository.findByBatch(batch);
    }

    @Override
    public List<Product> findByFragile(boolean fragile) {
        return this.jpaProductRepository.findByFragile(fragile);
    }

    @Override
    public List<Product> findByColor(String color) {
        return this.jpaProductRepository.findByColor(color);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.jpaProductRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<Product> findByPackaging(String packaging) {
        return this.jpaProductRepository.findByPackaging(ProductPackaging.valueOf(packaging));
    }
}
