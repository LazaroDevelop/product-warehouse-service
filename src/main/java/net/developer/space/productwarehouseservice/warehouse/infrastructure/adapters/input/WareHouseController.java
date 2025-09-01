package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.input;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.developer.space.productwarehouseservice.warehouse.application.constants.ApplicationConstants;
import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.application.dto.SectionDto;
import net.developer.space.productwarehouseservice.warehouse.application.ports.input.IService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouse")
public class WareHouseController {

    private final IService service;

    @Value("#{wareHouseService.allProducts.size()}")
    private Integer size;

    //Filtering collection using SePL
    @Value("#{wareHouseService.map.values().?[ productNumber != 8]}")
    private List<ProductDto> values;
    // Get the first value that match with this conditional
    @Value("#{wareHouseService.map.values().^[ productNumber != 8]}")
    private ProductDto first;
    // Get the last value that math with this conditional
    @Value("#{wareHouseService.map.values().$[ getProductNumber() != 8]}")
    private ProductDto last;

    //Filtering and getting a property from the result
    @Value("#{wareHouseService.map.values().?[ name == '${product.excluded-name}'].productNumber }")
    private Integer excludeProductNumber;

    //System properties predefined
    @Value("#{systemProperties['os.name']}")
    private String osName;

    // Ternary in SePL
    @Value("#{systemProperties['os.name'].toLowerCase().startsWith('Windows') ? 'hello windows' : 'hello others' }")
    private String helloMessage;


    @PostMapping("/create/section")
    public ResponseEntity<SectionDto> creatingTheSection(@RequestBody SectionDto section) {
        SectionDto sectionDto = this.service.createSection(section);
        if (section == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(sectionDto);
    }

    @PutMapping("/update/section/{id}")
    public ResponseEntity<SectionDto> updatingTheSection(@PathVariable long id, @RequestBody SectionDto section) {
        SectionDto result = this.service.updateSection(section, id);
        if (result == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/assign/section/{id}")
    public ResponseEntity<SectionDto> assignTheProducts(@PathVariable long id, @RequestBody List<ProductDto> products) {
        SectionDto result = this.service.assignProducts(products, id);
        if (result == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/section/{id}")
    public ResponseEntity<String> deletingTheSection(@PathVariable long id) {
        this.service.deleteSection(id);
        return ResponseEntity.ok(ApplicationConstants.DELETING_MESSAGE);
    }

    @PostMapping("/store/product")
    public ResponseEntity<ProductDto> storeTheProduct(@RequestBody ProductDto productDto) {
        ProductDto result = service.storeProduct(productDto);
        if (result == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all/products")
    public ResponseEntity<List<ProductDto>> findingAllTheProducts() {
        List<ProductDto> products = this.service.getAllProducts();
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/fragile/products")
    public ResponseEntity<List<ProductDto>> findingFragileProducts() {
        List<ProductDto> products = this.service.getFragileProducts();
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/range/prices/products")
    public ResponseEntity<List<ProductDto>> findingAllTheProductsByRangedPrice(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<ProductDto> products = this.service.getProductsInRangedPrice(minPrice, maxPrice);
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/colors/products")
    public ResponseEntity<List<ProductDto>> findingProductsByColor(@RequestParam String color) {
        List<ProductDto> products = this.service.getProductsByColor(color);
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/batch/products")
    public ResponseEntity<List<ProductDto>> findingProductsByBatch(@RequestParam String batch) {
        List<ProductDto> products = this.service.getProductsByBatch(batch);
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/packaging/products")
    public ResponseEntity<List<ProductDto>> findingProductsByPackage(@RequestParam String packagingType) {
        List<ProductDto> products = this.service.getProdcutsByPackaging(packagingType);
        if (products == null || products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }
}
