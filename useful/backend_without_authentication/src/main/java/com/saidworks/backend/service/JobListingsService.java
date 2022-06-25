package com.saidworks.backend.service;

import com.saidworks.backend.domain.JobListings;
import com.saidworks.backend.model.JobListingsDTO;
import com.saidworks.backend.repos.JobListingsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class JobListingsService {

    private final JobListingsRepository jobListingsRepository;

    public JobListingsService(final JobListingsRepository jobListingsRepository) {
        this.jobListingsRepository = jobListingsRepository;
    }

    public List<JobListingsDTO> findAll() {
        return jobListingsRepository.findAll()
                .stream()
                .map(jobListings -> mapToDTO(jobListings, new JobListingsDTO()))
                .collect(Collectors.toList());
    }

    public JobListingsDTO get(final Long id) {
        return jobListingsRepository.findById(id)
                .map(jobListings -> mapToDTO(jobListings, new JobListingsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final JobListingsDTO jobListingsDTO) {
        final JobListings jobListings = new JobListings();

        mapToEntity(jobListingsDTO, jobListings);
        return jobListingsRepository.save(jobListings).getId();
    }

    public void update(final Long id, final JobListingsDTO jobListingsDTO) {
        final JobListings jobListings = jobListingsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(jobListingsDTO, jobListings);
        jobListingsRepository.save(jobListings);
    }

    public void delete(final Long id) {
        jobListingsRepository.deleteById(id);
    }

    private JobListingsDTO mapToDTO(final JobListings jobListings,
            final JobListingsDTO jobListingsDTO) {
        jobListingsDTO.setId(jobListings.getId());
        jobListingsDTO.setTitle(jobListings.getTitle());
        jobListingsDTO.setCompany(jobListings.getCompany());
        jobListingsDTO.setLocation(jobListings.getLocation());
        jobListingsDTO.setDescription(jobListings.getDescription());
        jobListingsDTO.setNVacancies(jobListings.getNVacancies());
        jobListingsDTO.setStartDate(jobListings.getStartDate());
        return jobListingsDTO;
    }

    private JobListings mapToEntity(final JobListingsDTO jobListingsDTO,
            final JobListings jobListings) {
        jobListings.setTitle(jobListingsDTO.getTitle());
        jobListings.setCompany(jobListingsDTO.getCompany());
        jobListings.setLocation(jobListingsDTO.getLocation());
        jobListings.setDescription(jobListingsDTO.getDescription());
        jobListings.setNVacancies(jobListingsDTO.getNVacancies());
        jobListings.setStartDate(jobListingsDTO.getStartDate());
        return jobListings;
    }

}
