package com.saidworks.backend.service;

import com.saidworks.backend.domain.Resume;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.CustomUserBean;
import com.saidworks.backend.model.ResumeDTO;
import com.saidworks.backend.model.UserDTO;
import com.saidworks.backend.repos.ResumeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.saidworks.backend.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final UserRepository userRepository;

    public ResumeService(final ResumeRepository resumeRepository,
            UserRepository userRepository,
                         UserDetailsServiceImpl userDetailsServiceImpl) {
        this.resumeRepository = resumeRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userRepository = userRepository;
    }

    public List<ResumeDTO> findAll() {
        return resumeRepository.findAll()
                .stream()
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .collect(Collectors.toList());
    }

    public ResumeDTO get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserBean userDetails = (CustomUserBean) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).get();
        return resumeRepository.findByUser(user)
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ResumeDTO resumeDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserBean userDetails = (CustomUserBean) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).get();
        final Resume resume = new Resume();
        mapToEntity(resumeDTO, resume);
        System.out.println(resume);
        user.setResume(resume);
        //userRepository.save(user);
        resume.setUser(user);
        return resumeRepository.save(resume).getId();
    }

    public void update(final Long id, final ResumeDTO resumeDTO) {
        final Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(resumeDTO, resume);
        resumeRepository.save(resume);
    }

    public void delete(final Long id) {
        resumeRepository.deleteById(id);
    }

    private ResumeDTO mapToDTO(final Resume resume, final ResumeDTO resumeDTO) {
        resumeDTO.setId(resume.getId());
        resumeDTO.setHeadline(resume.getHeadline());
        resumeDTO.setPath(resume.getPath());
        return resumeDTO;
    }

    private Resume mapToEntity(final ResumeDTO resumeDTO, final Resume resume) {
        resume.setHeadline(resumeDTO.getHeadline());
        resume.setPath(resumeDTO.getPath());
        return resume;
    }

}
