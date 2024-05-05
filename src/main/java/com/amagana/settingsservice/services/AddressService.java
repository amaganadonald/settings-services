package com.amagana.settingsservice.services;

import java.util.List;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;

public interface AddressService {
	
	List<AddressResponseDTO> getAllAddress();
	AddressResponseDTO getAddressById(final Long id);
	AddressResponseDTO addAddress(AddressRequestDTO addressRequestDTO);
	AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO, final Long id);
	Void deleteAddress(final Long id);
}
