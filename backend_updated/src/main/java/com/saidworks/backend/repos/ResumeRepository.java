package com.saidworks.backend.repos;

import com.saidworks.backend.domain.Resume;
import com.saidworks.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByUser(User user);
}
