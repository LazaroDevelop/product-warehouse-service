package net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.warehouse.application.exceptions.UserNotFoundException;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JpaSectionRepositoryAdapter implements SectionRepository {

    private final JpaSectionRepository jpaSectionRepository;
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Section saveProducts(List<Product> productToSave, long sectionId) {

        Optional<Section> findingSection = this.jpaSectionRepository.findById(sectionId);
        if(findingSection.isPresent()){
            Section section = findingSection.get();
            for(Product product : productToSave){
                Product newProduct = this.jpaProductRepository.save(product);
                section.getProducts().add(newProduct);
            }
            return this.jpaSectionRepository.save(section);
        }
        throw new UserNotFoundException(String.format("User with id: %s not found", sectionId));
    }

    @Override
    public Section create(Section section) {
        return this.jpaSectionRepository.save(section);
    }

    @Override
    public Section update(Section section, long sectionId) {
        Optional<Section> optionalUpdate = this.jpaSectionRepository.findById(sectionId);
        if(optionalUpdate.isPresent()){
            Section sectionToUpdate = optionalUpdate.get();
            sectionToUpdate.setProductType(section.getProductType());
            sectionToUpdate.setProducts(section.getProducts());
            sectionToUpdate.setSize(section.getSize());
            return this.jpaSectionRepository.save(sectionToUpdate);
        }
        throw new UserNotFoundException(String.format("User with id: %s not found", sectionId));
    }

    @Override
    public void delete(long sectionId) {
        Optional<Section> findingSection = this.jpaSectionRepository.findById(sectionId);
        findingSection.ifPresent(this.jpaSectionRepository::delete);
        throw new UserNotFoundException(String.format("User with id: %s not found", sectionId));
    }

    @Override
    public Section findSectionById(long id) {
        Optional<Section> findingSection = this.jpaSectionRepository.findById(id);
        if(findingSection.isPresent()){
            return findingSection.get();
        }
       throw new UserNotFoundException(String.format("User with id: %s not found", id));
    }
}
