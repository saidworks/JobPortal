package com.saidworks.backend.controller;



import com.saidworks.backend.model.JobApplicationDTO;
import com.saidworks.backend.service.JobApplicationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final JobApplicationService jobApplicationService;
    public UserController(final JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;

    }

    @PostMapping("/{jobListingsId}/apply")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createJobApplication(
            @RequestBody @Valid final JobApplicationDTO jobApplicationDTO,
            @PathVariable final Long jobListingsId) {
        return new ResponseEntity<>(jobApplicationService.create(jobApplicationDTO,jobListingsId), HttpStatus.CREATED);
    }



}
