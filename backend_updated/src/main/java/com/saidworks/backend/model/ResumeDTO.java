package com.saidworks.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResumeDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String headline;

    @NotNull
    @Size(max = 255)
    private String path;

}
