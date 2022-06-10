package com.saidworks.backend.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.saidworks.backend.domain.Role;
import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.AuthResponse;
import com.saidworks.backend.model.CustomUserBean;
import com.saidworks.backend.model.Roles;
import com.saidworks.backend.model.SignupRequest;
import com.saidworks.backend.repos.RoleRepository;
import com.saidworks.backend.repos.UserRepository;
import com.saidworks.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody User user) {
        //System.out.println("AuthController -- userLogin");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateJwtToken(authentication);
        CustomUserBean userBean = (CustomUserBean) authentication.getPrincipal();
        List<String> roles = userBean.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setRoles(roles);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody SignupRequest signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        User user = new User();
        Set<Role> roles = new HashSet<>();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setCourse(signupRequest.getCourse());
        user.setCampus(signupRequest.getCampus());
        System.out.println("Encoded password--- " + user.getPassword());
        String[] roleArr = signupRequest.getRoles();
        System.out.println(Arrays.toString(roleArr));
        System.out.println(roleRepository.findByRoleName(Roles.ROLE_USER).get());
        if(roleArr == null) {
            roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get()) ;
        }
        for(String role: roleArr) {
            switch(role) {
                case "Admin":
                    roles.add(roleRepository.findByRoleName(Roles.ROLE_ADMIN).get());
                    break;
                case "User":
                    System.out.println(Roles.ROLE_USER);
                    System.out.println(roleRepository.findByRoleName(Roles.ROLE_USER).isPresent());
                    if(roleRepository.findByRoleName(Roles.ROLE_USER).isPresent()){
                    roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());}
                    break;
                    //I need to add condition because default run anytime
//                default:
//                    return ResponseEntity.badRequest().body("Specified role not found");
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User signed up successfully");
    }
}