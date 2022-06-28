package com.saidworks.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobApplicationsDTO {

    private LocalDate applicationDate;

    private String path;
}
