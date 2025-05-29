package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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
		} catch (SpecialtyNotFound e) {
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

}
