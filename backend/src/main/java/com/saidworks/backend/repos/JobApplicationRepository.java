package com.saidworks.backend.repos;

import com.saidworks.backend.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
