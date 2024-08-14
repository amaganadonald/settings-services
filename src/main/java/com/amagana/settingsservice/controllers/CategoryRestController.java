package com.amagana.settingsservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.settingsservice.dto.ApiResponse;
import com.amagana.settingsservice.dto.CategoryRequestDTO;
import com.amagana.settingsservice.dto.CategoryResponseDTO;
import com.amagana.settingsservice.enums.StatusResponse;
import com.amagana.settingsservice.services.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategory() {
		List<CategoryResponseDTO> category = categoryService.getAllCategory();
		return new ResponseEntity<>(ApiResponse.list(StatusResponse.SUCCESS, category), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
		CategoryResponseDTO category = categoryService.getCategoryById(id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> addCategory(@RequestBody CategoryRequestDTO categoryResquestDTO) {
		CategoryResponseDTO category = categoryService.addCategory(categoryResquestDTO);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCatgory(@PathVariable Long id, 
			@RequestBody CategoryRequestDTO categoryResquestDTO) {
		CategoryResponseDTO category = categoryService.updateCategory(categoryResquestDTO, id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> deleteCatgory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(ApiResponse.singleMessage(StatusResponse.SUCCESS, "Catgory deleted successfull"), HttpStatus.OK);
	}
	
	@GetMapping("/fgc/{id}")
	public CategoryResponseDTO categoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
}
