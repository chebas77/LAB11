package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "specialties")
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String office;

    @Column(name = "h_open")
    private int h_open;

    @Column(name = "h_close")
    private int h_close;

    public Specialty() {
    }

    public Specialty(Integer id, String name, String office, int h_open, int h_close) {
        super();
        this.id = id;
        this.name = name;
        this.office = office;
        this.h_open = h_open;
        this.h_close = h_close;
    }

    public Specialty(String name, String office, int h_open, int h_close) {
        super();
        this.name = name;
        this.office = office;
        this.h_open = h_open;
        this.h_close = h_close;
    }
}