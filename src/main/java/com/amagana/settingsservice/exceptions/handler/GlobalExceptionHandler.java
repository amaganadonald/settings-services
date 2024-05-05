package com.amagana.settingsservice.exceptions.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amagana.settingsservice.dto.ApiResponse;
import com.amagana.settingsservice.dto.ErrorsDTO;
import com.amagana.settingsservice.enums.StatusResponse;
import com.amagana.settingsservice.exceptions.EntityNotFoundException;
import com.amagana.settingsservice.exceptions.SettingsServiceBussnessException;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> entitiesValidationFailed(MethodArgumentNotValidException exception) {		
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.errors(errors)
				.status(StatusResponse.FAILED)
				.message("Error occur when validation field")
				.build();
		log.error("Error message {} ",apiResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	

	@ExceptionHandler(SettingsServiceBussnessException.class)
	public ResponseEntity<ApiResponse<?>> onlineBookBusinessException(SettingsServiceBussnessException exception) {
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.FAILED)
				.message(exception.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> entityNotFoundException(EntityNotFoundException exception) {
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.FAILED)
				.message(exception.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}

}
