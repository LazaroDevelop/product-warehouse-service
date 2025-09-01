package net.developer.space.productwarehouseservice.warehouse.application;

import java.math.BigDecimal;
import java.util.*;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.application.dto.SectionDto;
import net.developer.space.productwarehouseservice.warehouse.application.mapper.ProductMapper;
import net.developer.space.productwarehouseservice.warehouse.application.mapper.SectionMapper;
import net.developer.space.productwarehouseservice.warehouse.application.ports.input.IService;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductType;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.ProductRepository;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.SectionRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class WareHouseService implements IService {

    private final SectionRepository sectionManager;
    private final ProductRepository productManager;

    @Getter
    private Map<Integer, ProductDto> map;

    @Override
    public SectionDto createSection(@NotNull SectionDto sectionToCreate) {
        Section section = new Section();
        if(sectionToCreate.getId() != 0) section.setId(sectionToCreate.getId());
        section.setSize(sectionToCreate.getSize());
        section.setProductType(ProductType.valueOf(sectionToCreate.getProductType()));
        section.setProducts(new HashSet<>());
        Section createdSection = this.sectionManager.create(section);
        return SectionDto.builder()
                .id(createdSection.getId())
                .size(createdSection.getSize())
                .productType(createdSection.getProductType().name())
                .products(SectionMapper.mapSetProductDto(section.getProducts()))
                .build();
    }

    @Override
    public SectionDto updateSection(SectionDto sectionToUpdate, long id) {
        return SectionMapper.mapToSectionDto(sectionManager.update(SectionMapper.mapToSection(sectionToUpdate), id));
    }

    @Override
    public SectionDto assignProducts(@NotNull List<ProductDto> productsToAssign, long sectionId) {
        List<Product> assignedProducts = new LinkedList<>();
        for (ProductDto productDto : productsToAssign) {
            assignedProducts.add(ProductMapper.mapProduct(productDto));
        }
        return SectionMapper.mapToSectionDto(sectionManager.saveProducts(assignedProducts, sectionId));
    }

    @Override
    public void deleteSection(long sectionId) {
        this.sectionManager.delete(sectionId);
        log.debug("User with id: {} was deleted", sectionId);
    }

    @Override
    public ProductDto storeProduct(ProductDto productToCreate) {
        Product product = ProductMapper.mapProduct(productToCreate);
        Product createdProduct = this.productManager.create(product);
        return ProductMapper.mapToProductDto(createdProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = this.productManager.findAll();
        List<ProductDto> result = new LinkedList<>();
        for (Product product : products) {
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

    @Override
    public List<ProductDto> getProductsByBatch(String batch) {
        List<Product> products = this.productManager.findByBatch(batch);
        List<ProductDto> result = new LinkedList<>();
        for (Product product : products) {
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

    @Override
    public List<ProductDto> getFragileProducts() {
        List<Product> products = this.productManager.findByFragile(true);
        List<ProductDto> result = new LinkedList<>();
        for (Product product : products) {
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

    @Override
    public List<ProductDto> getProductsByColor(String color) {
        List<Product> products = this.productManager.findByColor(color);
        List<ProductDto> result = new LinkedList<>();
        for (Product product : products) {
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

    @Override
    public List<ProductDto> getProductsInRangedPrice(BigDecimal min, BigDecimal max) {
        List<Product> products = this.productManager.findByPriceBetween(min, max);
        List<ProductDto> result = new LinkedList<>();
        for(Product product : products){
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

    @Override
    public List<ProductDto> getProdcutsByPackaging(String packaging) {
        List<Product> products = this.productManager.findByPackaging(packaging);
        List<ProductDto> result = new LinkedList<>();
        for(Product product : products){
            result.add(ProductMapper.mapToProductDto(product));
        }
        return result;
    }

}
