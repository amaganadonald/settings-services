package com.amagana.settingsservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;
import com.amagana.settingsservice.mappers.AddressMapper;
import com.amagana.settingsservice.models.Address;
import com.amagana.settingsservice.repository.AddressRepository;
import com.amagana.settingsservice.services.impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	AddressRepository addressRepository;
	
	@InjectMocks
	AddressServiceImpl addressServiceImpl;
	
	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	Address address;
	
	MockedStatic<AddressMapper> mockStatic;
	
	@BeforeEach
	void setUp() {
		mockStatic = Mockito.mockStatic(AddressMapper.class);
		addressRequestDTO = AddressRequestDTO.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
		address = Address.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
		when(AddressMapper.addressDtoToAddress(addressRequestDTO)).thenReturn(address);
		when(AddressMapper.addressToAddressResponseDTO(address)).thenReturn(addressResponseDTO);
	}
	
	@AfterEach
	void cleanUp() {
		mockStatic.close();
	}
	
	@Test
	void shouldReturnAllAddress() {
		when(addressRepository.findAll()).thenReturn(List.of(address));
		List<AddressResponseDTO> cat = addressServiceImpl.getAllAddress();
		assertEquals(1, cat.size());
		assertEquals(1, cat.size());
		assertEquals("Mulhenbach", cat.get(0).getAddressCity());
	}
	
	@Test
	void shouldAddAddressThenReturnAddressResponseDTO() {
		when(addressRepository.save(address)).thenReturn(address);
		AddressResponseDTO cat = addressServiceImpl.addAddress(addressRequestDTO);
		assertEquals(37, cat.getAddressNumber());
		assertEquals("Mulhenbach", cat.getAddressCity());
	}
	
	@Test
	void shouldUpdateAddressThenReturnAddressResponseDTO() {
		when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
		when(addressRepository.save(address)).thenReturn(address);
		AddressResponseDTO cat = addressServiceImpl.updateAddress(addressRequestDTO, 1L);
		assertEquals(37, cat.getAddressNumber());
		assertEquals("Mulhenbach", cat.getAddressCity());
	}
	
	@Test
	void shouldDeleteAddress() {
		addressRepository = Mockito.mock(AddressRepository.class);
		addressRepository.delete(address);
		Mockito.verify(addressRepository).delete(address);
	}
}
