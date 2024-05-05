package com.amagana.settingsservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.mappers.CategoryMapper;
import com.amagana.settingsservice.models.Category;
import com.amagana.settingsservice.repository.CategoryRepository;
import com.amagana.settingsservice.services.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	
	CategoryRequestDTO categoryRequestDTO;
	CategoryResponseDTO categoryResponseDTO;
	Category category;
	
	MockedStatic<CategoryMapper> mockStatic;
	
	@BeforeEach
	void setUp() {
		mockStatic = mockStatic(CategoryMapper.class);
		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		categoryResponseDTO = CategoryResponseDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		category = Category.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		when(CategoryMapper.categoryRequestDTOToCategory(categoryRequestDTO)).thenReturn(category);
		when(CategoryMapper.categoryToCategoryResponseDTO(category)).thenReturn(categoryResponseDTO);
	}
	
	@AfterEach
	void cleanUp() {
		mockStatic.close();
	}
	
	@Test
	void shouldReturnAllCategory() {
		when(categoryRepository.findAll()).thenReturn(List.of(category));
		List<CategoryResponseDTO> cat = categoryServiceImpl.getAllCategory();
		assertEquals(1, cat.size());
		assertEquals("Roman", cat.get(0).getCategoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldAddCategoryThenReturnCategoryResponseDTO() {
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryResponseDTO cat = categoryServiceImpl.addCategory(categoryRequestDTO);
		assertEquals("Roman", cat.getCategoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldUpdateCategoryThenReturnCategoryResponseDTO() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryResponseDTO cat = categoryServiceImpl.updateCategory(categoryRequestDTO, 1L);
		assertEquals("Roman", cat.getCategoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldDeleteCategory() {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryRepository.delete(category);
		Mockito.verify(categoryRepository).delete(category);
	}
}
