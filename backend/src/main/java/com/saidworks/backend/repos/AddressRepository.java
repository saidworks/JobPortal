package com.saidworks.backend.repos;

import com.saidworks.backend.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
