package com.amagana.settingsservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.models.Category;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {

	@Mock
	CategoryRepository categoryRepository;
    Category category;
	
	@BeforeEach
	void setUp() {
		category = Category.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
	}

	@Test
	void testFindAllAddress() {
		when(categoryRepository.findAll()).thenReturn(List.of(category));
		List<Category> categoryRequest = categoryRepository.findAll();
		assertEquals(1, categoryRequest.size());
		assertEquals("Roman", categoryRequest.get(0).getCategoryName());
		assertNotNull(categoryRequest);
	}
	
	@Test
	void testFindAddressById() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		Category categoryRequest = categoryRepository.findById(1L).get();
		assertEquals("Roman en livre", categoryRequest.getCategoryDescription());
		assertEquals("Roman", categoryRequest.getCategoryName());
	}
	
	@Test
	void testAddAddressInDatabase() {
		Category category2 = Category.builder()
				.categoryDescription("Romantique")
				.categoryName("Harlequin")
				.build();
		when(categoryRepository.save(category2)).thenReturn(category2);
		Category categoryRequest = categoryRepository.save(category2);
		assertEquals("Romantique", categoryRequest.getCategoryDescription());
		assertEquals("Harlequin", categoryRequest.getCategoryName());
	}
	
	@Test
	void shouldUpdateAddress() {
		category.setCategoryDescription("Fiction");
		category.setCategoryName("Film");;
		when(categoryRepository.save(category)).thenReturn(category);
		Category categoryRequest = categoryRepository.save(category);
		assertEquals("Fiction", categoryRequest.getCategoryDescription());
		assertEquals("Film", categoryRequest.getCategoryName());
	}
	
	@Test
	void shouldDeleteAddressById() {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryRepository.delete(category);
		Mockito.verify(categoryRepository).delete(category);
	}
	
}
