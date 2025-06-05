package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SpecialtyDTO {
    private Integer id;
    private String name;
    private String office;
    private int h_open;
    private int h_close;
}


