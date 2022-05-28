package com.saidworks.backend.rest;

import com.saidworks.backend.model.JobListingsDTO;
import com.saidworks.backend.service.JobListingsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/jobListingss", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobListingsResource {

    private final JobListingsService jobListingsService;

    public JobListingsResource(final JobListingsService jobListingsService) {
        this.jobListingsService = jobListingsService;
    }

    @GetMapping
    public ResponseEntity<List<JobListingsDTO>> getAllJobListingss() {
        return ResponseEntity.ok(jobListingsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobListingsDTO> getJobListings(@PathVariable final Long id) {
        return ResponseEntity.ok(jobListingsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createJobListings(
            @RequestBody @Valid final JobListingsDTO jobListingsDTO) {
        return new ResponseEntity<>(jobListingsService.create(jobListingsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobListings(@PathVariable final Long id,
            @RequestBody @Valid final JobListingsDTO jobListingsDTO) {
        jobListingsService.update(id, jobListingsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteJobListings(@PathVariable final Long id) {
        jobListingsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
