package net.developer.space.productwarehouseservice.warehouse.domain.model;

import lombok.*;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductType;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@ToString
@Table(name = "Section", indexes = {
        @Index(name = "idx_section_id", columnList = "id")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue
    private long id;

    private float size;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    
    @Builder.Default
    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new LinkedHashSet<>();
}
