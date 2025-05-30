package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Specialty;

@Repository
public interface SpecialtyRepository
        extends JpaRepository<Specialty, Integer> {


    // Create a new specialty
	Specialty save(Specialty specialty);
    // Fetch specialties by name
    List<Specialty> findByName(String name);

    // Fetch specialties by office
    List<Specialty> findByOffice(String office);


    @Override
    List<Specialty> findAll();

}
