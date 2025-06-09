package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	private Specialty created;


	@Test
	public void testFindById() {

		Integer ID = 1;
		String NAME_EXPECTED = "radiology";

		Specialty specialty = null;

		try {
			specialty = this.specialtyService.findById(ID);
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(NAME_EXPECTED, specialty.getName());
	}
	@Test
	public void testFindByName() {

		String FIND_NAME = "surgery";
		int SIZE_EXPECTED = 1;

		List<Specialty> specialties = this.specialtyService.findByName(FIND_NAME);

		assertEquals(SIZE_EXPECTED, specialties.size());
	}
	@Test
	public void testCreate() {

		String NAME = "cardiology";
		String OFFICE = "Lima";
		int H_OPEN = 8;
		int H_CLOSE = 17;

		Specialty specialty = new Specialty(NAME, OFFICE, H_OPEN, H_CLOSE);

		Specialty specialtyCreated = this.specialtyService.create(specialty);

		assertNotNull(specialtyCreated.getId());
		assertEquals(NAME, specialtyCreated.getName());
		assertEquals(OFFICE, specialtyCreated.getOffice());
		assertEquals(H_OPEN, specialtyCreated.getH_open());
		assertEquals(H_CLOSE, specialtyCreated.getH_close());
	}
	@Test
	public void testDelete() {

		Integer ID_TO_DELETE = 10;

		// Eliminar la especialidad por ID
		try {
			this.specialtyService.delete(ID_TO_DELETE);
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}

		// Verificar que ya no existe
		try {
			this.specialtyService.findById(ID_TO_DELETE);
			fail("Specialty should have been deleted");
		} catch (SpecialtyNotFoundException e) {
			// Se espera esta excepción
			assertTrue(true);
		}
	}

}
