package com.amagana.settingsservice.services;

import java.util.List;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;

public interface CategoryService {

	List<CategoryResponseDTO> getAllCategory();
	CategoryResponseDTO getCategoryById(Long id);
	CategoryResponseDTO addCategory(CategoryRequestDTO categoryResquestDTO);
	CategoryResponseDTO updateCategory(CategoryRequestDTO categoryResquestDTO, Long id);
	Void deleteCategory (Long id);
}
