package net.developer.space.productwarehouseservice.warehouse.application.ports.input;


import java.math.BigDecimal;
import java.util.List;

import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.application.dto.SectionDto;

public interface IService {

    SectionDto createSection(SectionDto sectionToCreate);
    SectionDto updateSection(SectionDto sectionToUpdate, long id);
    SectionDto assignProducts(List<ProductDto> productsToAssign, long id);
    void deleteSection(long sectionId);

    ProductDto storeProduct(ProductDto productToCreate);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByBatch(String batch);
    List<ProductDto> getFragileProducts();
    List<ProductDto> getProductsByColor(String color);
    List<ProductDto> getProductsInRangedPrice(BigDecimal min, BigDecimal max);
    List<ProductDto> getProdcutsByPackaging(String packaging);

}
