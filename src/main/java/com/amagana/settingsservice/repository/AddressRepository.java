package com.amagana.settingsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.settingsservice.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
