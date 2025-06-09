package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;

import java.util.List;

@RestController
@Slf4j
public class SpecialtyController {

    String h_open=null;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private SpecialtyMapper mapper;


    public SpecialtyController(SpecialtyService specialtyService, SpecialtyMapper mapper){
        this.specialtyService = specialtyService;
        this.mapper = mapper ;
    }

    @GetMapping(value = "/specialties")
    public List<SpecialtyDTO> getSpecialties(){
        List<Specialty> specialties = specialtyService.findAll();
        log.info("specialties: " + specialties);
        specialties.forEach(item -> log.info("Specialty >>  {} ", item));

        List<SpecialtyDTO> specialtiesTO = this.mapper.toSpecialtyTOList(specialties);
        log.info("specialtiesTO: " + specialtiesTO);
        specialtiesTO.forEach(item -> log.info("SpecialtyTO >>  {} ", item));

        return specialtiesTO;
    }


    @PostMapping(value = "/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<SpecialtyDTO> create(@RequestBody SpecialtyDTO specialtyTO) {

        Specialty newSpecialty = this.mapper.toSpecialty(specialtyTO);
        SpecialtyDTO newSpecialtyTO = this.mapper.toSpecialtyDTO(specialtyService.create(newSpecialty));

        return  ResponseEntity.status(HttpStatus.CREATED).body(newSpecialtyTO);


    }
    /**
     * Find specialty by id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/specialties/{id}")
    ResponseEntity<SpecialtyDTO> findById(@PathVariable Integer id) {

        SpecialtyDTO specialtyTO = null;

        try {
            Specialty specialty = specialtyService.findById(id);
            specialtyTO = this.mapper.toSpecialtyDTO(specialty);

        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specialtyTO);
    }

    @PutMapping(value = "/specialties/{id}")
    ResponseEntity<SpecialtyDTO> update(@RequestBody SpecialtyDTO specialtyTO, @PathVariable Integer id) {

        SpecialtyDTO updateSpecialtyTO = null;

        try {

            Specialty updateSpecialty = specialtyService.findById(id);

            updateSpecialty.setName(specialtyTO.getName());
            updateSpecialty.setH_open(specialtyTO.getH_open());
            updateSpecialty.setH_close(specialtyTO.getH_close());
            updateSpecialty.setOffice(specialtyTO.getOffice());

            specialtyService.update(updateSpecialty);

            updateSpecialtyTO = this.mapper.toSpecialtyDTO(updateSpecialty);

        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updateSpecialtyTO);
    }
    /**
     * Delete specialty by id
     *
     * @param id
     */
    @DeleteMapping(value = "/specialties/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id) {

        try {
            specialtyService.delete(id);
            return ResponseEntity.ok(" Delete ID :" + id);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



}