package com.saidworks.backend.rest;

import com.saidworks.backend.model.JobListingsDTO;
import com.saidworks.backend.service.JobListingsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping(value = "/api/jobListingss", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobListingsResource {

    private final JobListingsService jobListingsService;

    public JobListingsResource(final JobListingsService jobListingsService) {
        this.jobListingsService = jobListingsService;
    }
//    @PreAuthorize("#username == authentication.principal.username")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<JobListingsDTO>> getAllJobListingss() {
        return ResponseEntity.ok(jobListingsService.findAll());
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<JobListingsDTO> getJobListings(@PathVariable final Long id) {
        return ResponseEntity.ok(jobListingsService.get(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createJobListings(
            @RequestBody @Valid final JobListingsDTO jobListingsDTO) {
        return new ResponseEntity<>(jobListingsService.create(jobListingsDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobListings(@PathVariable final Long id,
            @RequestBody @Valid final JobListingsDTO jobListingsDTO) {
        jobListingsService.update(id, jobListingsDTO);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteJobListings(@PathVariable final Long id) {
        jobListingsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
