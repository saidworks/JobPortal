package com.saidworks.backend.repos;

import com.saidworks.backend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
