//package com.saidworks.backend.controller;
//
//
//import com.saidworks.backend.domain.User;
//import com.saidworks.backend.model.ResumeDTO;
//import com.saidworks.backend.service.ResumeService;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
//public class UserController {
//    private final ResumeService resumeService;
//    public UserController(final ResumeService resumeService) {
//        this.resumeService = resumeService;}
//
//    @GetMapping("resume/{id}")
//    public ResponseEntity<ResumeDTO> getResume(@PathVariable final Long id){
////        Authentication authentication =
////                SecurityContextHolder.getContext().getAuthentication();
////        User user = (User) authentication.getPrincipal();
//        return ResponseEntity.ok(resumeService.get(id));
//
//    }
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Long> createResume(@RequestBody @Valid final ResumeDTO resumeDTO) {
//        return new ResponseEntity<>(resumeService.create(resumeDTO), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateResume(@PathVariable final Long id,
//                                             @RequestBody @Valid final ResumeDTO resumeDTO) {
//        resumeService.update(id, resumeDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteResume(@PathVariable final Long id) {
//        resumeService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
