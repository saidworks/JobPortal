package com.saidworks.backend.service;

import com.saidworks.backend.domain.Resume;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.ResumeDTO;
import com.saidworks.backend.repos.ResumeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(final ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public List<ResumeDTO> findAll() {
        return resumeRepository.findAll()
                .stream()
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .collect(Collectors.toList());
    }

    public ResumeDTO get() {
       Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
       User user = (User) authentication.getPrincipal();
        return resumeRepository.findByUser(user)
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ResumeDTO resumeDTO) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        final Resume resume = new Resume();
        resume.setUser(user);
        mapToEntity(resumeDTO, resume);
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
