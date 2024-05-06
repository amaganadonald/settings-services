package com.amagana.settingsservice.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.mappers.CategoryMapper;
import com.amagana.settingsservice.models.Category;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

	CategoryRequestDTO categoryRequestDTO;
	Category category;
	
	@BeforeEach
	void setUp() {
		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		category = Category.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
	}
	
	@Test
	void testShouldConvertCatgoryToCategoryResponseDTO() {
		CategoryResponseDTO categoryResponseDTO = CategoryMapper.categoryToCategoryResponseDTO(category);
		assertNotNull(categoryResponseDTO);
		assertEquals("Roman", categoryResponseDTO.getCategoryName());
	}
	
	@Test
	void testShouldCatgoryRequestDtoToCategory() {
		Category category = CategoryMapper.categoryRequestDTOToCategory(categoryRequestDTO);
		assertNotNull(category);
		assertEquals("Roman", category.getCategoryName());
		assertEquals("Roman en livre", category.getCategoryDescription());
	}
}
