package net.developer.space.productwarehouseservice.warehouse.application.mapper;

import java.util.*;

import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.application.dto.SectionDto;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductType;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;
import org.jetbrains.annotations.NotNull;

public class SectionMapper {

    public static Section mapToSection(@NotNull SectionDto sectionDto){
        Section section = new Section();
        section.setProductType(ProductType.valueOf(sectionDto.getProductType()));
        section.setSize(sectionDto.getSize());
        section.setProducts(mapSetProduct(sectionDto.getProducts()));
        return section;
    }
    

    public static SectionDto mapToSectionDto(@NotNull Section section){
        return SectionDto.builder()
                .id(section.getId())
                .size(section.getSize())
                .productType(section.getProductType().name())
                .products(mapSetProductDto(section.getProducts()))
                .build();
    }

    public static Set<Product> mapSetProduct(@NotNull Set<ProductDto> productDtos){
        Set<Product> products = new HashSet<>();

        for(ProductDto productDto : productDtos){
            products.add(ProductMapper.mapProduct(productDto));
        }

        return products;
    }

    public static Set<ProductDto> mapSetProductDto(@NotNull Set<Product> products){
        Set<ProductDto> productDtos = new HashSet<>();

        for(Product product : products){
            productDtos.add(ProductMapper.mapToProductDto(product));
        } 

        return productDtos;
    }
}
