package net.developer.space.productwarehouseservice.warehouse.application.mapper;

import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductPackaging;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;

public class ProductMapper {

    public static Product mapProduct(ProductDto productDto){
        Product product = new Product();
        product.setBatch(productDto.getBatch());
        product.setColor(productDto.getColor());
        product.setFragile(productDto.isFragile());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setPackaging(ProductPackaging.valueOf(productDto.getPackaging()));
        product.setSize(productDto.getSize());
        return product;
    }

    public static ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setBatch(product.getBatch());
        productDto.setColor(product.getColor());
        productDto.setFragile(product.isFragile());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setPackaging(product.getPackaging().name());
        productDto.setSize(product.getSize());
        return productDto;
    }
    
}
