package com.saidworks.backend.service;

import com.saidworks.backend.domain.Role;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.RoleDTO;
import com.saidworks.backend.repos.RoleRepository;
import com.saidworks.backend.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(final RoleRepository roleRepository, final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .collect(Collectors.toList());
    }

    public RoleDTO get(final Long id) {
        return roleRepository.findById(id)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getId();
    }

    public void update(final Long id, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Long id) {
        roleRepository.deleteById(id);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setUser(role.getUser() == null ? null : role.getUser().getId());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setRoleName(roleDTO.getRoleName());
        if (roleDTO.getUser() != null && (role.getUser() == null || !role.getUser().getId().equals(roleDTO.getUser()))) {
            final User user = userRepository.findById(roleDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            role.setUser(user);
        }
        return role;
    }

}
