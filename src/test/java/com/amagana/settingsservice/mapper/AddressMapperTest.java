package com.amagana.settingsservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;
import com.amagana.settingsservice.mappers.AddressMapper;
import com.amagana.settingsservice.models.Address;

@ExtendWith(MockitoExtension.class)
class AddressMapperTest {

	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	Address address;
	
	@BeforeEach
	void setUp() {
		addressRequestDTO =	AddressRequestDTO.builder()
				.addressName("Lux ville")
				.addressCity("Hamilus")
				.addressNumber(37)
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.id(1L)
				.build();
		address = Address.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.build();
	}
	
	@Test
	void testToMapAddressToAddressResponseDTO() {
		AddressResponseDTO response = AddressMapper.addressToAddressResponseDTO(address);
		assertNotNull(response);
		assertEquals("Mulhenbachh", response.getAddressCity());
	}
	
	@Test
	void testToMapAddressRequestToAddress() {
		Address response = AddressMapper.addressDtoToAddress(addressRequestDTO);
		assertNotNull(response);
		assertEquals("Lux ville", response.getAddressName());
		assertEquals("Hamilus", response.getAddressCity());
	}
	
}
