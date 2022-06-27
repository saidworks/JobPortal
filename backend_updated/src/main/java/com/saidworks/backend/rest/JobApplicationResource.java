package com.saidworks.backend.rest;

import com.saidworks.backend.model.JobApplicationDTO;
import com.saidworks.backend.service.JobApplicationService;
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
@RequestMapping(value = "/api/jobApplications", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobApplicationResource {

    private final JobApplicationService jobApplicationService;

    public JobApplicationResource(final JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDTO>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> getJobApplication(@PathVariable final Long id) {
        return ResponseEntity.ok(jobApplicationService.get(id));
    }

//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Long> createJobApplication(
//            @RequestBody @Valid final JobApplicationDTO jobApplicationDTO) {
//        return new ResponseEntity<>(jobApplicationService.create(jobApplicationDTO), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobApplication(@PathVariable final Long id,
            @RequestBody @Valid final JobApplicationDTO jobApplicationDTO) {
        jobApplicationService.update(id, jobApplicationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable final Long id) {
        jobApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
