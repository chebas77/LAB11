package com.tecsup.petclinic.services;
import java.util.List;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.entities.Specialty;

public interface SpecialtyService {

    /**
     *
     * @param specialty
     * @return
     */
    Specialty create(Specialty specialty);
    /**
     *
     * @param specialty
     * @return
     */
    Specialty update(Specialty specialty);
    /**
     *
     * @param id
     * @throws SpecialtyNotFoundException
     */
    void delete(Integer id) throws SpecialtyNotFoundException;
    /**
     *
     * @param id
     * @return
     */
    Specialty findById(Integer id) throws SpecialtyNotFoundException;
    /**
     *
     * @param name
     * @return
     */
    List<Specialty> findByName(String name);
    /**
     *
     * @return
     */
    List<Specialty> findByOffice(String office);
    /**
     *
     * @return
     */
    List<Specialty> findAll();


}
