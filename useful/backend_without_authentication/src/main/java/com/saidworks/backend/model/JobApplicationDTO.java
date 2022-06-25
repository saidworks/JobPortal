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

    @NotNull
    @Size(max = 255)
    private String user;

    @Size(max = 255)
    private String jobListing;

    private LocalDate applicationDate;

    @Size(max = 255)
    private String resumeId;

    private Long jobListings;

    private Long oneUser;

}
