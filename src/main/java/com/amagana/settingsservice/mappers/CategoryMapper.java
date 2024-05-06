package com.amagana.settingsservice.mappers;

import org.modelmapper.ModelMapper;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.models.Category;


public class CategoryMapper {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	private CategoryMapper() {
		
	}
	public static Category categoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
		return modelMapper.map(categoryRequestDTO, Category.class);
	}
	
	public static CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
		return modelMapper.map(category, CategoryResponseDTO.class);
	}
}
