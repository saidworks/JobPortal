package com.saidworks.backend.repos;

import com.saidworks.backend.domain.Address;
import com.saidworks.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUser(User user);
}
