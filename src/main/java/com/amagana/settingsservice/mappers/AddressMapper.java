package com.amagana.settingsservice.mappers;

import org.modelmapper.ModelMapper;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;
import com.amagana.settingsservice.models.Address;

public class AddressMapper {
	
	public static Address addressDtoToAddress(AddressRequestDTO addressRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(addressRequestDTO, Address.class);
	}
	
	public static AddressResponseDTO addressToAddressResponseDTO(Address address) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(address, AddressResponseDTO.class);
	}
}
