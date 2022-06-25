package com.saidworks.backend.repos;

import com.saidworks.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
