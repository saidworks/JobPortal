package com.saidworks.backend.controller;



import com.saidworks.backend.domain.JobApplication;
import com.saidworks.backend.domain.JobListings;
import com.saidworks.backend.domain.Resume;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.JobApplicationsDTO;
import com.saidworks.backend.repos.JobApplicationRepository;
import com.saidworks.backend.repos.JobListingsRepository;
import com.saidworks.backend.repos.ResumeRepository;
import com.saidworks.backend.repos.UserRepository;
import com.saidworks.backend.service.JobApplicationService;
import com.saidworks.backend.service.ResumeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final ResumeRepository resumeRepository;
    private final JobListingsRepository jobListingsRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;
    AdminController(final ResumeRepository resumeRepository,
                    final JobApplicationRepository jobApplicationRepository,
                    final JobListingsRepository jobListingsRepository,
                    final UserRepository userRepository){
        this.resumeRepository = resumeRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobListingsRepository = jobListingsRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/{jobListingsId}")
    public ResponseEntity<List<JobApplicationsDTO>> getJobApplications(@PathVariable Long jobListingsId){
        JobListings jobListings = this.jobListingsRepository.findById(jobListingsId).get();
//        System.out.println(jobListingsId);
//        System.out.println(jobListings);
        Set<JobApplication> jobApplications = this.jobApplicationRepository.findByJobListings(jobListings).get();
//        System.out.println(jobApplications);
        List<JobApplicationsDTO> theJobApplications = new ArrayList<>();

        Iterator<JobApplication> elements = jobApplications.iterator();

        while(elements.hasNext()){
            JobApplication current = elements.next();
            JobApplicationsDTO application = mapToDTO(current);
            theJobApplications.add(application);
        }
        return ResponseEntity.ok(theJobApplications);
    }
    public JobApplicationsDTO mapToDTO(JobApplication jobApplication){
        JobApplicationsDTO jobApplicationsDTO = new JobApplicationsDTO();
        jobApplicationsDTO.setApplicationDate(jobApplication.getApplicationDate());
        User user = jobApplication.getCandidate();
        Resume resume = user.getResume();
        jobApplicationsDTO.setPath(resume.getPath());
        return jobApplicationsDTO;
    }
}
