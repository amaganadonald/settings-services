package com.amagana.settingsservice.mappers;

import org.modelmapper.ModelMapper;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.models.Category;


public class CategoryMapper {
	
	public static Category categoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoryRequestDTO, Category.class);
	}
	
	public static CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(category, CategoryResponseDTO.class);
	}
}
