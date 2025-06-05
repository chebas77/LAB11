package com.tecsup.petclinic.util;

import com.tecsup.petclinic.dtos.PetDTO;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Specialty;
import java.util.ArrayList;
import java.util.List;

public class TObjectCreator {

	// ================= PET METHODS (EXISTING) =================

	public static Pet getPet() {
		return new Pet(1,"Leo",1,1, null);
	}

	public static Pet newPet() {
		return new Pet(0,"Punky",1,1, null);
	}

	public static Pet newPetCreated() {
		Pet pet = newPet();
		pet.setId(1000);
		return pet;
	}

	public static Pet newPetForUpdate() {
		return new Pet(0,"Bear",1,1,null);
	}

	public static Pet newPetCreatedForUpdate() {
		Pet pet = newPetForUpdate();
		pet.setId(4000);
		return pet;
	}

	public static Pet newPetForDelete() {
		return new Pet(0,"Bird",1,1, null);
	}

	public static Pet newPetCreatedForDelete() {
		Pet pet = newPetForDelete();
		pet.setId(2000);
		return pet;
	}

	public static List<PetDTO> getAllPetTOs() {
		List<PetDTO> petTOs  = new ArrayList<PetDTO>();
		petTOs.add(new PetDTO(1,"Leo",1,1, "2000-09-07"));
		petTOs.add(new PetDTO(2,"Basil",6,2, "2002-08-06"));
		petTOs.add(new PetDTO(3,"Rosy",2,3, "2001-04-17"));
		petTOs.add(new PetDTO(4,"Jewel",2,3, "2000-03-07"));
		petTOs.add(new PetDTO(5,"Iggy",3,4, "2000-11-30"));
		return petTOs;
	}

	public static List<Pet> getPetsForFindByName() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(1,"Leo",1,1, null));
		return pets;
	}

	public static List<Pet> getPetsForFindByTypeId() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(9,"Lucky",5,7, null));
		pets.add(new Pet(11,"Freddy",5,9, null));
		return pets;
	}

	public static List<Pet> getPetsForFindByOwnerId() {
		List<Pet> pets  = new ArrayList<Pet>();
		pets.add(new Pet(12,"Lucky",2,10, null));
		pets.add(new Pet(13,"Sly",1,10, null));
		return pets;
	}

	public static PetDTO getPetTO() {
		return new PetDTO(1,"Leo",1,1, "2000-09-07");
	}

	public static PetDTO newPetTO() {
		return new PetDTO(-1,"Beethoven",1,1, "2020-05-20");
	}

	public static PetDTO newPetTOForDelete() {
		return new PetDTO(10000,"Beethoven3",1,1, "2020-05-20");
	}

	// ================= SPECIALTY METHODS (NEW) =================

	public static Specialty getSpecialty() {
		return new Specialty(1, "radiology", "Farewell", 8, 18);
	}

	public static Specialty newSpecialty() {
		return new Specialty(0, "Dermatology", "Room 205", 9, 16);
	}

	public static Specialty newSpecialtyCreated() {
		Specialty specialty = newSpecialty();
		specialty.setId(1000);
		return specialty;
	}

	public static Specialty newSpecialtyForUpdate() {
		return new Specialty(0, "Neurology", "Room 401", 8, 18);
	}

	public static Specialty newSpecialtyCreatedForUpdate() {
		Specialty specialty = newSpecialtyForUpdate();
		specialty.setId(4000);
		return specialty;
	}

	public static Specialty newSpecialtyForDelete() {
		return new Specialty(0, "Radiology", "Room 301", 7, 15);
	}

	public static Specialty newSpecialtyCreatedForDelete() {
		Specialty specialty = newSpecialtyForDelete();
		specialty.setId(2000);
		return specialty;
	}

	public static List<SpecialtyDTO> getAllSpecialtyTOs() {
		List<SpecialtyDTO> specialtyTOs = new ArrayList<SpecialtyDTO>();
		specialtyTOs.add(new SpecialtyDTO(1, "radiology", "Farewell", 8, 18));
		specialtyTOs.add(new SpecialtyDTO(2, "surgery", "Maryland", 8, 12));
		specialtyTOs.add(new SpecialtyDTO(3, "dentistry", "Terranova", 9, 19));
		specialtyTOs.add(new SpecialtyDTO(9, "cardiology", "Lima", 8, 17));
		specialtyTOs.add(new SpecialtyDTO(11, "cardiology", "Lima", 8, 17));
		specialtyTOs.add(new SpecialtyDTO(12, "cardiology", "Lima", 8, 17));
		return specialtyTOs;
	}

	public static List<Specialty> getSpecialtiesForFindByName() {
		List<Specialty> specialties = new ArrayList<Specialty>();
		specialties.add(new Specialty(1, "radiology", "Farewell", 8, 18));
		return specialties;
	}

	public static List<Specialty> getSpecialtiesForFindByOffice() {
		List<Specialty> specialties = new ArrayList<Specialty>();
		specialties.add(new Specialty(9, "cardiology", "Lima", 8, 17));
		specialties.add(new Specialty(11, "cardiology", "Lima", 8, 17));
		specialties.add(new Specialty(12, "cardiology", "Lima", 8, 17));
		return specialties;
	}

	public static SpecialtyDTO getSpecialtyTO() {
		return new SpecialtyDTO(1, "radiology", "Farewell", 8, 18);
	}

	public static SpecialtyDTO newSpecialtyTO() {
		return new SpecialtyDTO(-1, "Dermatology", "Room 205", 9, 16);
	}

	public static SpecialtyDTO newSpecialtyTOForDelete() {
		return new SpecialtyDTO(10000, "Radiology", "Room 301", 7, 15);
	}
}