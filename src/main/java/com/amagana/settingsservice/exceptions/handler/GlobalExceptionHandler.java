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
	public ResponseEntity<ApiResponse<ErrorsDTO>> entitiesValidationFailed(MethodArgumentNotValidException exception) {		
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		log.error("Error message {} ",errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.errors(StatusResponse.FAILED,
				"Error occur when validation field", errors));
	}
	

	@ExceptionHandler(SettingsServiceBussnessException.class)
	public ResponseEntity<ApiResponse<String>> onlineBookBusinessException(SettingsServiceBussnessException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.singleMessage(StatusResponse.FAILED, 
				exception.getMessage()));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> entityNotFoundException(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.singleMessage(StatusResponse.FAILED, 
				exception.getMessage()));
	}

}
