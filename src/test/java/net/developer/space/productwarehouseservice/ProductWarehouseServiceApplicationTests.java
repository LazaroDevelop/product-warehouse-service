package net.developer.space.productwarehouseservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.*;

import net.developer.space.productwarehouseservice.auth.infrastructure.adapters.input.DemoController;
import net.developer.space.productwarehouseservice.warehouse.application.dto.ProductDto;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductPackaging;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Product;
import net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output.JpaProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import net.developer.space.productwarehouseservice.warehouse.application.WareHouseService;
import net.developer.space.productwarehouseservice.warehouse.application.dto.SectionDto;
import net.developer.space.productwarehouseservice.warehouse.domain.enums.ProductType;
import net.developer.space.productwarehouseservice.warehouse.domain.model.Section;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.ProductRepository;
import net.developer.space.productwarehouseservice.warehouse.domain.repository.SectionRepository;
import net.developer.space.productwarehouseservice.warehouse.infrastructure.adapters.output.JpaSectionRepository;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest
class ProductWarehouseServiceApplicationTests {

	@Autowired
	WareHouseService service;

	@Mock
	ProductRepository productManager;

	@Mock
	SectionRepository sectionManager;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName(value = "Testing create section feature")
	void testCreateSection() {
		Section sectionToCreate = new Section();
		sectionToCreate.setId(0L);
		sectionToCreate.setProductType(ProductType.HOME_APPLIANCES);
		sectionToCreate.setProducts(new HashSet<>());

		SectionDto createdSection = SectionDto.builder()
				.id(sectionToCreate.getId())
				.productType(ProductType.HOME_APPLIANCES.name())
				.products(new HashSet<>())
				.build();

		when(this.sectionManager.create(sectionToCreate)).thenReturn(sectionToCreate);

		SectionDto newSection = this.service.createSection(createdSection);

		assertEquals(newSection.getId(), createdSection.getId());
		assertEquals(createdSection.getProductType(), newSection.getProductType());
		assertEquals(createdSection.getProducts(), newSection.getProducts());
	}

	@Test
	@DisplayName(value = "Testing update section feature")
	void testUpdateSection(){

		Section sectionToUpdate = new Section();
		sectionToUpdate.setId(1L);
		sectionToUpdate.setProductType(ProductType.HOME_APPLIANCES);
		sectionToUpdate.setSize(5);
		sectionToUpdate.setProducts(new HashSet<>());

		SectionDto updateDto = SectionDto.builder()
				.id(sectionToUpdate.getId())
				.productType(ProductType.HOME_APPLIANCES.name())
				.products(new HashSet<>())
				.size(5)
				.build();

		Section sectionUpdated = new Section();
		sectionUpdated.setId(sectionToUpdate.getId());
		sectionUpdated.setProductType(ProductType.MEATS);
		sectionUpdated.setProducts(new HashSet<>());
		sectionUpdated.setSize(10);

		SectionDto updatedSection = SectionDto.builder()
				.id(sectionToUpdate.getId())
				.productType(ProductType.MEATS.name())
				.products(new HashSet<>())
				.size(10)
				.build();

		when(this.sectionManager.create(sectionToUpdate)).thenReturn(sectionToUpdate);

		this.service.createSection(updateDto);

		when(this.sectionManager.update(sectionToUpdate, 1L)).thenReturn(sectionUpdated);

		SectionDto updateSectionDto = this.service.updateSection(updatedSection, 1L);

		assertEquals(updatedSection.getId(), updateSectionDto.getId());
		assertEquals(updatedSection.getProductType(), updateSectionDto.getProductType());
		assertEquals(updatedSection.getProducts(), updateSectionDto.getProducts());
	}

	@Test
	@DisplayName("Testing assign some products in a determinate section feature")
	void testAssignSectionProducts(){

		Section section = new Section();
		section.setId(2L);
		section.setSize(56.65f);
		section.setProductType(ProductType.MEATS);

		SectionDto sectionToCreate = SectionDto.builder()
				.id(2L)
				.size(56.65f)
				.productType(ProductType.MEATS.name())
				.build();

		List<ProductDto> productDtos = new LinkedList<>();

		List<Product> products = List.of(new Product(
				0L, 0.5f, "eggs", "X1290DOCFX00", "white", true, BigDecimal.valueOf(2381.32), ProductPackaging.CARDBOARD
		), new Product(
				1L, 0.932f, "chicken salad", "SJKJUIA18931", "dark orange", false, BigDecimal.valueOf(2.9092), ProductPackaging.PLASTIC
		), new Product(
				2L, 12.321f, "beef meat", "KAIJA1278823", "red", false, BigDecimal.valueOf(32.4389), ProductPackaging.NYLON
		));

		for(Product product : products){
			when(this.productManager.create(product)).thenReturn(product);
			productDtos.add(ProductDto.builder()
					.id(product.getId())
					.batch(product.getBatch())
					.color(product.getColor())
					.price(product.getPrice())
					.fragile(product.isFragile())
					.packaging(product.getPackaging().name())
					.name(product.getName())
					.size(product.getSize())
					.build());
			this.service.storeProduct(ProductDto.builder()
					.id(product.getId())
					.batch(product.getBatch())
					.color(product.getColor())
					.price(product.getPrice())
					.fragile(product.isFragile())
					.packaging(product.getPackaging().name())
					.name(product.getName())
					.size(product.getSize())
					.build()
			);
		}

		section.setProducts(new HashSet<>());
		sectionToCreate.setProducts(new HashSet<>());

		when(this.sectionManager.create(section)).thenReturn(section);

		SectionDto sDto = this.service.createSection(sectionToCreate);

		when(this.sectionManager.saveProducts(products, section.getId())).thenReturn(section);

		SectionDto sectionDto = this.service.assignProducts(productDtos, section.getId());

		assertEquals(sectionDto.getId(), sectionDto.getId());
	}

}
