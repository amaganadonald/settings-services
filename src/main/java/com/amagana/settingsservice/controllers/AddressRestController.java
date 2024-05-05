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
import org.springframework.web.bind.annotation.RestController;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;
import com.amagana.settingsservice.dto.ApiResponse;
import com.amagana.settingsservice.enums.StatusResponse;
import com.amagana.settingsservice.services.AddressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
@Slf4j
public class AddressRestController {

	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<?>> getAllAddress() {
		log.info("AddressRestController:getAllAddress starting call service for retrieving all Address");
		List<AddressResponseDTO> addressResponseDTO = addressService.getAllAddress();
		log.info("AddressRestController:getAllAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAllAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	/*@GetMapping("/page")
	public ResponseEntity<ApiResponse<?>> getAllAddressByPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "10") int size) {
		log.info("AddressRestController:getAllAddressByPage starting call service for retrieving Address by page size");
		Page<AddressResponseDTO> addressResponseDTO = addressService.getAllAddressPage(page, size);
		log.info("AddressRestController:getAllAddressByPage start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAllAddressByPage end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}*/

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> getAddressById(@PathVariable Long id) {
		log.info("AddressRestController:getAddressById starting call service for retrieving addresse with id {}", id);
		AddressResponseDTO addressResponseDTO = addressService.getAddressById(id);
		log.info("AddressRestController:getAddressById start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAddressById end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<?>> addAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO) {
		log.info("AddressRestController:addAddress starting call service for add  Address");
		AddressResponseDTO addressResponseDTO = addressService.addAddress(addressRequestDTO);
		log.info("AddressRestController:addAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:addAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> updateAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO ,@PathVariable Long id) {
		log.info("AddressRestController:updateAddress starting call service for updated  Address with id {}", id);
		AddressResponseDTO addressResponseDTO = addressService.updateAddress(addressRequestDTO, id);
		log.info("AddressRestController:updateAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:updateAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteAddress(@PathVariable Long id) {
		log.info("AddressRestController:deleteAddress starting call service for delete Address with id {}", id);
		addressService.deleteAddress(id);
		log.info("AddressRestController:deleteAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusResponse.SUCCESS)
				.message("Address deleted successfull")
				.build();
		log.info("AddressRestController:deleteAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/ofc/{id}")
	public AddressResponseDTO findAddressById(@PathVariable Long id) {
		return addressService.getAddressById(id);
	}
}
