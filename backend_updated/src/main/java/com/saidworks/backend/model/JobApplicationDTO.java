package com.saidworks.backend.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobApplicationDTO {

    private Long id;


    private LocalDate applicationDate;


    private Long jobListings;

    private Long candidate;

}
