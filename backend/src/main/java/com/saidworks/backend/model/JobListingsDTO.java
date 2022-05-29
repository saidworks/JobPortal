package com.saidworks.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobListingsDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String company;

    @NotNull
    @Size(max = 255)
    private String location;

    @NotNull
    @Size(max = 4000)
    private String description;

    @Size(max = 255)
    @JsonProperty("nVacancies")
    private String nVacancies;

    private LocalDate startDate;

}
