package com.saidworks.backend.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String state;

}
