package com.saidworks.backend.service;

import com.saidworks.backend.domain.Address;
import com.saidworks.backend.domain.JobApplication;
import com.saidworks.backend.domain.Resume;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.UserDTO;
import com.saidworks.backend.repos.AddressRepository;
import com.saidworks.backend.repos.JobApplicationRepository;
import com.saidworks.backend.repos.ResumeRepository;
import com.saidworks.backend.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ResumeRepository resumeRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public UserService(final UserRepository userRepository,
            final AddressRepository addressRepository, final ResumeRepository resumeRepository,
            final JobApplicationRepository jobApplicationRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.resumeRepository = resumeRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCourse(user.getCourse());
        userDTO.setCampus(user.getCampus());
        userDTO.setAddress(user.getAddress() == null ? null : user.getAddress().getId());
        userDTO.setResume(user.getResume() == null ? null : user.getResume().getId());
        userDTO.setJobApplication(user.getJobApplication() == null ? null : user.getJobApplication().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCourse(userDTO.getCourse());
        user.setCampus(userDTO.getCampus());
        if (userDTO.getAddress() != null && (user.getAddress() == null || !user.getAddress().getId().equals(userDTO.getAddress()))) {
            final Address address = addressRepository.findById(userDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            user.setAddress(address);
        }
        if (userDTO.getResume() != null && (user.getResume() == null || !user.getResume().getId().equals(userDTO.getResume()))) {
            final Resume resume = resumeRepository.findById(userDTO.getResume())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resume not found"));
            user.setResume(resume);
        }
        if (userDTO.getJobApplication() != null && (user.getJobApplication() == null || !user.getJobApplication().getId().equals(userDTO.getJobApplication()))) {
            final JobApplication jobApplication = jobApplicationRepository.findById(userDTO.getJobApplication())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "jobApplication not found"));
            user.setJobApplication(jobApplication);
        }
        return user;
    }

}
