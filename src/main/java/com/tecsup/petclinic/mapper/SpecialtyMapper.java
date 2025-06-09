package com.tecsup.petclinic.mapper;
import com.tecsup.petclinic.dtos.PetDTO;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Specialty;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Mapper (componentModel = "spring", nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT)
public interface SpecialtyMapper {
    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    @Mapping(source = "h_open", target = "h_open")
    Specialty toSpecialty(SpecialtyDTO specialtyDTO);

    default Date toDate(String date) {
        Date dateObj = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            dateObj = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateObj;
    }

    @Mapping(source = "h_open", target = "h_open")
    SpecialtyDTO toSpecialtyDTO(Specialty specialty);
    default String toString(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            return dateFormat.format(date);
        } else {
            return "";
        }
    }


    List<SpecialtyDTO> toSpecialtyTOList(List<Specialty> specialties);
    List<Specialty> toSpecialtyList(List<SpecialtyDTO> specialtyDTOList);
}