package net.developer.space.productwarehouseservice.warehouse.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductPackaging;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "Product", indexes = {
        @Index(name = "idx_product_id_section_id", columnList = "id, section_id")
})
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float size;

    private String name;

    private String batch;

    private String color;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    private boolean fragile;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductPackaging packaging;

    public Product(long id, float size, String name, String batch, String color, boolean fragile, BigDecimal price,
            ProductPackaging packaging) {
        this.id = id;
        this.size = size;
        this.name = name;
        this.batch = batch;
        this.color = color;
        this.fragile = fragile;
        this.price = price;
        this.packaging = packaging;
    }
}
