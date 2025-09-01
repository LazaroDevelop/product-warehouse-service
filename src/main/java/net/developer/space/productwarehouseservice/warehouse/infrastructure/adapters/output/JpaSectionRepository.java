package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output;

import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSectionRepository extends JpaRepository<Section, Long> {
}