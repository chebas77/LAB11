package com.tecsup.petclinic.services;
import java.util.List;
import java.util.Optional;

import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFound;


@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;

        /**
         *
         * @param specialty
         * @return
         */
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }
    /**
     *
     * @param specialty
     * @return
     */
    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }
    /**
     *
     * @param id
     * @throws SpecialtyNotFound
     */
    @Override
    public void delete(Integer id) throws SpecialtyNotFound {
        Specialty specialty = findById(id);
        specialtyRepository.delete(specialty);
    }
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Specialty findById(Integer id) throws SpecialtyNotFound {

        Optional<Specialty> specialty = specialtyRepository.findById(id);

        if (!specialty.isPresent()) {
            log.error("Specialty with id {} not found", id);
            throw new SpecialtyNotFound("Specialty with id " + id + " not found");
        }
        return specialty.get();
    }
    /**
     *
     * @param name
     * @return
     */
    @Override
    public List<Specialty> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        specialties.stream().forEach(specialty -> log.info("" + specialty));
        return specialties;

    }
    /**
     *
     *
     * @return
     */
    @Override
    public List<Specialty> findByOffice(String office) {
        List<Specialty> specialties = specialtyRepository.findByOffice(office);
        specialties.stream().forEach(specialty -> log.info("" + specialty));
        return specialties;
    }
    /**
     *
     * @return
     */
    @Override
    public  List<Specialty>findAll() {

        return specialtyRepository.findAll();
    }


}
