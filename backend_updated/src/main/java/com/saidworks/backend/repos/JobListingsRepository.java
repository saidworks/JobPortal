package com.saidworks.backend.repos;

import com.saidworks.backend.domain.JobListings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobListingsRepository extends JpaRepository<JobListings, Long> {
}
