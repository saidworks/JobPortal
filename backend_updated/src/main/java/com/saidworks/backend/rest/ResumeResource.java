package com.saidworks.backend.rest;

import com.saidworks.backend.model.ResumeDTO;
import com.saidworks.backend.service.ResumeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/resumes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResumeResource {

    private final ResumeService resumeService;

    public ResumeResource(final ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public ResponseEntity<List<ResumeDTO>> getAllResumes() {
        return ResponseEntity.ok(resumeService.findAll());
    }

    @GetMapping("mycv")
    public ResponseEntity<ResumeDTO> getResume() {
        return ResponseEntity.ok(resumeService.get());
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createResume(@RequestBody @Valid final ResumeDTO resumeDTO) {
        return new ResponseEntity<>(resumeService.create(resumeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResume(@PathVariable final Long id,
            @RequestBody @Valid final ResumeDTO resumeDTO) {
        resumeService.update(id, resumeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteResume(@PathVariable final Long id) {
        resumeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
