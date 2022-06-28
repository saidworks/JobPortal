package com.saidworks.backend.repos;

import com.saidworks.backend.domain.JobApplication;
import com.saidworks.backend.domain.JobListings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Optional<Set<JobApplication>> findByJobListings(JobListings jobListings);
}
