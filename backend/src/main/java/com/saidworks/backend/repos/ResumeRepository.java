package com.saidworks.backend.repos;

import com.saidworks.backend.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
