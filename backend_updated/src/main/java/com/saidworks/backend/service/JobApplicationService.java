package com.saidworks.backend.service;

import com.saidworks.backend.domain.JobApplication;
import com.saidworks.backend.domain.JobListings;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.CustomUserBean;
import com.saidworks.backend.model.JobApplicationDTO;
import com.saidworks.backend.repos.JobApplicationRepository;
import com.saidworks.backend.repos.JobListingsRepository;
import com.saidworks.backend.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobListingsRepository jobListingsRepository;
    private final UserRepository userRepository;

    public JobApplicationService(final JobApplicationRepository jobApplicationRepository,
                                 final JobListingsRepository jobListingsRepository,
                                 final UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobListingsRepository = jobListingsRepository;
        this.userRepository = userRepository;
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

    public Long create(final JobApplicationDTO jobApplicationDTO,final Long jobListingsId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserBean userDetails = (CustomUserBean) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).get();
        JobListings job = jobListingsRepository.findById(jobListingsId).get();
        final JobApplication jobApplication = new JobApplication();
        mapToEntity(jobApplicationDTO, jobApplication);
        jobApplication.setCandidate(user);
        jobApplication.setJobListings(job);
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
        jobApplicationDTO.setApplicationDate(jobApplication.getApplicationDate());
        jobApplicationDTO.setJobListings(jobApplication.getJobListings() == null ? null : jobApplication.getJobListings().getId());
        jobApplicationDTO.setCandidate(jobApplication.getCandidate() == null ? null : jobApplication.getCandidate().getId());
        return jobApplicationDTO;
    }

    private JobApplication mapToEntity(final JobApplicationDTO jobApplicationDTO,
                                       final JobApplication jobApplication) {
        jobApplication.setApplicationDate(jobApplicationDTO.getApplicationDate());
        if (jobApplicationDTO.getJobListings() != null && (jobApplication.getJobListings() == null || !jobApplication.getJobListings().getId().equals(jobApplicationDTO.getJobListings()))) {
            final JobListings jobListings = jobListingsRepository.findById(jobApplicationDTO.getJobListings())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jobListings not found"));
            jobApplication.setJobListings(jobListings);
        }
        if (jobApplicationDTO.getCandidate() != null && (jobApplication.getCandidate() == null || !jobApplication.getCandidate().getId().equals(jobApplicationDTO.getCandidate()))) {
            final User candidate = userRepository.findById(jobApplicationDTO.getCandidate())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "candidate not found"));
            jobApplication.setCandidate(candidate);
        }
        return jobApplication;
    }

}
