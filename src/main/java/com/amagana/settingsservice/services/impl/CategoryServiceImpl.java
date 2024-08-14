package com.amagana.settingsservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.exceptions.SettingsServiceBussnessException;
import com.amagana.settingsservice.mappers.CategoryMapper;
import com.amagana.settingsservice.models.Category;
import com.amagana.settingsservice.repository.CategoryRepository;
import com.amagana.settingsservice.services.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryResponseDTO> getAllCategory() {
		log.info("AddressService:getAllCategory starting fetch all category");
		return categoryRepository.findAll()
				.stream()
				.map(CategoryMapper::categoryToCategoryResponseDTO)
				.toList();
	}
	
	public Category getCategoryByIds(Long id) {
		log.info("CategoryService:getCategoryByIds starting to fetch category by id::"+ id);
		 if (id == null || id <= 0) {
	            throw new IllegalArgumentException("Invalid user ID");
	        }

		try {
			Optional<Category> category = categoryRepository.findById(id);
			log.info("CategoryService:getCategoryByIds fetch address with message::"+ category.toString());
			return category.get();
			
		} catch (Exception e) {
			log.error("AddressServie:getCategoryByIds fetch category by id failed with message::"+e.getMessage());
			throw new SettingsServiceBussnessException("fetch category by id failed with message::" + e.getMessage());
		}
		
	}

	@Override
	public CategoryResponseDTO getCategoryById(Long id) {
		return CategoryMapper.categoryToCategoryResponseDTO(getCategoryByIds(id));
	}

	@Override
	public CategoryResponseDTO addCategory(CategoryRequestDTO categoryResquestDTO) {
		try {
			Category category = CategoryMapper.categoryRequestDTOToCategory(categoryResquestDTO);
			categoryRepository.save(category);
			return CategoryMapper.categoryToCategoryResponseDTO(category);
		} catch (Exception e) {
			log.error("CategoryService:addCategory add category failed with message::"+ e.getMessage());
			throw new SettingsServiceBussnessException("add category failed with message::" + e.getMessage());
		}
	}

	@Override
	public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryResquestDTO, Long id) {
		Category category = getCategoryByIds(id);
		try {
			category.setCategoryName(categoryResquestDTO.getCategoryName());
			category.setCategoryDescription(categoryResquestDTO.getCategoryDescription());
			categoryRepository.save(category);
		} catch (Exception e) {
			log.error("CategoryService:updateCategory update category by id failed with message::"+ e.getMessage());
			throw new SettingsServiceBussnessException("update category by id failed with message" + e.getMessage());
		}
		return CategoryMapper.categoryToCategoryResponseDTO(category);
	}

	@Override
	public Void deleteCategory(Long id) {
		Category category = getCategoryByIds(id);
		try {
			categoryRepository.delete(category);
		} catch (Exception e) {
			log.error("Error deleted category with message {}", e.getMessage());
			throw new SettingsServiceBussnessException("Error deleted category with message" + e.getMessage());
		}
		return null;
	}

	
}
