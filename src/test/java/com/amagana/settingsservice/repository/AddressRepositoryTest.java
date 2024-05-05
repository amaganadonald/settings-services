package com.amagana.settingsservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amagana.settingsservice.models.Address;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryTest {
	
	@Mock
	AddressRepository addressRepository;
	
	Address address;
	
	@BeforeEach
	void setUp() {
		address = Address.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
	}

	@Test
	void testFindAllAddress() {
		when(addressRepository.findAll()).thenReturn(List.of(address));
		List<Address> addressRequest = addressRepository.findAll();
		assertEquals(1, addressRequest.size());
		assertEquals("Mulhenbach", addressRequest.get(0).getAddressCity());
	}
	
	@Test
	void testFindAddressById() {
		when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
		Address addressRequest = addressRepository.findById(1L).get();
		assertEquals(37, addressRequest.getAddressNumber());
		assertEquals("Mulhenbach", addressRequest.getAddressCity());
	}
	
	@Test
	void testAddAddressInDatabase() {
		Address address2 = Address.builder()
				.addressCity("Hamilus")
				.addressEmail("evrard@gmail.com")
				.addressName("Lux")
				.addressNumber(40)
				.addressPhone("62157663737")
				.addressProfessionalPhone("6845664545")
				.build();
		when(addressRepository.save(address2)).thenReturn(address2);
		Address addressRequest = addressRepository.save(address2);
		assertEquals(40, addressRequest.getAddressNumber());
		assertEquals("Hamilus", addressRequest.getAddressCity());
	}
	
	@Test
	void shouldUpdateAddress() {
		address.setAddressCity("Hamilus");
		address.setAddressNumber(40);
		when(addressRepository.save(address)).thenReturn(address);
		Address addressRequest = addressRepository.save(address);
		assertEquals(40, addressRequest.getAddressNumber());
		assertEquals("Hamilus", addressRequest.getAddressCity());
	}
	
	@Test
	void shouldDeleteAddressById() {
		addressRepository = Mockito.mock(AddressRepository.class);
		addressRepository.delete(address);
		Mockito.verify(addressRepository).delete(address);
	}
}
