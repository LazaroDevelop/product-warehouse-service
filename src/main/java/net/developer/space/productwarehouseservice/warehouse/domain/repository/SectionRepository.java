package net.developer.space.productwarehouseservice.warehouse.domain.repository;

import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;

import java.util.List;

public interface SectionRepository {
    Section findSectionById(long id);
    Section saveProducts(List<Product> productToSave, long sectionId);
    Section create(Section section);
    Section update(Section section, long sectionId);
    void delete(long sectionId);
}