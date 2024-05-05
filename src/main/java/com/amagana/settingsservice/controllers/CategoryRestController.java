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
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/Category")
@Slf4j
public class CategoryRestController {

    private CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ApiResponse<?>> getAllCategory() {
		List<CategoryResponseDTO> category = categoryService.getAllCategory();
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(category)
				.build();
		log.info("CategoryRestController:getAllCategory end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> getCategoryById(@PathVariable Long id) {
		log.info("CategoryRestController:getCategoryById starting call service for retrieving Category with id {}", id);
		CategoryResponseDTO category = categoryService.getCategoryById(id);
		log.info("CategoryRestController:getCategoryById start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(category)
				.build();
		log.info("CategoryRestController:getCategoryById end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<?>> addCategory(@RequestBody CategoryRequestDTO categoryResquestDTO) {
		log.info("CategoryRestController:addCategory starting call service for add  Category");
		CategoryResponseDTO category = categoryService.addCategory(categoryResquestDTO);
		log.info("CategoryRestController:addCategory start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(category)
				.build();
		log.info("CategoryRestController:addCategory end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> updateCatgory(@PathVariable Long id, 
			@RequestBody CategoryRequestDTO categoryResquestDTO) {
		log.info("CategoryRestController:updateCatgory starting call service for updated Catgory with id {}", id);
		CategoryResponseDTO category = categoryService.updateCategory(categoryResquestDTO, id);
		log.info("CategoryRestController:updateCatgory start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(category)
				.build();
		log.info("CategoryRestController:updateCatgory end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteCatgory(@PathVariable Long id) {
		log.info("CategoryRestController:deleteCatgory starting call service for delete Catgory with id {}", id);
		categoryService.deleteCategory(id);
		log.info("CategoryRestController:deleteCatgory start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.message("Catgory deleted successfull")
				.build();
		log.info("CategoryRestController:deleteCatgory end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/fgc/{id}")
	public CategoryResponseDTO categoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
}
