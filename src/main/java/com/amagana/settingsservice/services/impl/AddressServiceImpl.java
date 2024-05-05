package com.amagana.settingsservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amagana.settingsservice.dto.AddressRequestDTO;
import com.amagana.settingsservice.dto.AddressResponseDTO;
import com.amagana.settingsservice.exceptions.SettingsServiceBussnessException;
import com.amagana.settingsservice.mappers.AddressMapper;
import com.amagana.settingsservice.models.Address;
import com.amagana.settingsservice.repository.AddressRepository;
import com.amagana.settingsservice.services.AddressService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService{

	private AddressRepository addressRepository;
	
	@Override
	public List<AddressResponseDTO>  getAllAddress() {
		log.info("AddressService:getAllAddress starting fetch all addresss");
		return addressRepository.findAll()
				.stream()
				.map(AddressMapper::addressToAddressResponseDTO)
				.peek(add->log.info("Successfully fecth all address with value {} ", add))
				.toList();
	}
	
	public Address getAddressByIds(Long id) {
		log.info("AddressServie:getAddressById starting to fetch address by id {} ", id);
		 if (id == null || id <= 0) {
	            throw new IllegalArgumentException("Invalid user ID");
	        }

		try {
			Optional<Address> address = addressRepository.findById(id);
			log.info("AddressServie:getAddressById fetch address with message {} ", address.toString());
			return address.get();
			
		} catch (Exception e) {
			log.error("AddressServie:getAddressById fetch address by id faile with message {} ", e.getMessage());
			throw new SettingsServiceBussnessException("fetch address by id faile with message {} " + e.getMessage());
		}
		
	}


	@Override
	public AddressResponseDTO getAddressById(Long id) {
		return AddressMapper.addressToAddressResponseDTO(getAddressByIds(id));
	}

	@Override
	public AddressResponseDTO addAddress(AddressRequestDTO addressRequestDTO) {
		try {
			Address address = AddressMapper.addressDtoToAddress(addressRequestDTO);
			addressRepository.save(address);
			return AddressMapper.addressToAddressResponseDTO(address);
		} catch (Exception e) {
			log.error("AddressServie:addAddress add address failed with message {} ", e.getMessage());
			throw new SettingsServiceBussnessException("add address failed with message {} " + e.getMessage());
		}
	}

	@Override
	public AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO, Long id) {
		Address address = getAddressByIds(id);
		try {
			address.setAddressCity(addressRequestDTO.getAddressCity());
			address.setAddressName(addressRequestDTO.getAddressName());
			address.setAddressNumber(addressRequestDTO.getAddressNumber());
			address.setAddressEmail(addressRequestDTO.getAddressEmail());
			address.setAddressPhone(addressRequestDTO.getAddressPhone());
			address.setAddressProfessionalPhone(address.getAddressProfessionalPhone());
			addressRepository.save(address);
		} catch (Exception e) {
			log.error("AddressServie:updateAddress update address by id failed with message {} ", e.getMessage());
			throw new SettingsServiceBussnessException("update address by id failed with message {} " + e.getMessage());
		}
		return AddressMapper.addressToAddressResponseDTO(address);
	}

	@Override
	public Void deleteAddress (Long id) {
		Address address = getAddressByIds(id);
		try {
			addressRepository.delete(address);
		} catch (Exception e) {
			log.error("Error deleted address with message {}", e.getMessage());
			throw new SettingsServiceBussnessException("Error deleted address with message" + e.getMessage());
		}
		return null;
	}

}
