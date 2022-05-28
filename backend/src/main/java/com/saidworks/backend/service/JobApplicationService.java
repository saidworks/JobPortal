package com.saidworks.backend.service;

import com.saidworks.backend.domain.JobApplication;
import com.saidworks.backend.domain.JobListings;
import com.saidworks.backend.model.JobApplicationDTO;
import com.saidworks.backend.repos.JobApplicationRepository;
import com.saidworks.backend.repos.JobListingsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobListingsRepository jobListingsRepository;

    public JobApplicationService(final JobApplicationRepository jobApplicationRepository,
            final JobListingsRepository jobListingsRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobListingsRepository = jobListingsRepository;
    }

    public List<JobApplicationDTO> findAll() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(jobApplication -> mapToDTO(jobApplication, new JobApplicationDTO()))
                .collect(Collectors.toList());
    }

    public JobApplicationDTO get(final Long id) {
        return jobApplicationRepository.findById(id)
                .map(jobApplication -> mapToDTO(jobApplication, new JobApplicationDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final JobApplicationDTO jobApplicationDTO) {
        final JobApplication jobApplication = new JobApplication();
        mapToEntity(jobApplicationDTO, jobApplication);
        return jobApplicationRepository.save(jobApplication).getId();
    }

    public void update(final Long id, final JobApplicationDTO jobApplicationDTO) {
        final JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(jobApplicationDTO, jobApplication);
        jobApplicationRepository.save(jobApplication);
    }

    public void delete(final Long id) {
        jobApplicationRepository.deleteById(id);
    }

    private JobApplicationDTO mapToDTO(final JobApplication jobApplication,
            final JobApplicationDTO jobApplicationDTO) {
        jobApplicationDTO.setId(jobApplication.getId());
        jobApplicationDTO.setUser(jobApplication.getUser());
        jobApplicationDTO.setJobListing(jobApplication.getJobListing());
        jobApplicationDTO.setApplicationDate(jobApplication.getApplicationDate());
        jobApplicationDTO.setResumeId(jobApplication.getResumeId());
        jobApplicationDTO.setJobListings(jobApplication.getJobListings() == null ? null : jobApplication.getJobListings().getId());
        return jobApplicationDTO;
    }

    private JobApplication mapToEntity(final JobApplicationDTO jobApplicationDTO,
            final JobApplication jobApplication) {
        jobApplication.setUser(jobApplicationDTO.getUser());
        jobApplication.setJobListing(jobApplicationDTO.getJobListing());
        jobApplication.setApplicationDate(jobApplicationDTO.getApplicationDate());
        jobApplication.setResumeId(jobApplicationDTO.getResumeId());
        if (jobApplicationDTO.getJobListings() != null && (jobApplication.getJobListings() == null || !jobApplication.getJobListings().getId().equals(jobApplicationDTO.getJobListings()))) {
            final JobListings jobListings = jobListingsRepository.findById(jobApplicationDTO.getJobListings())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jobListings not found"));
            jobApplication.setJobListings(jobListings);
        }
        return jobApplication;
    }

}
