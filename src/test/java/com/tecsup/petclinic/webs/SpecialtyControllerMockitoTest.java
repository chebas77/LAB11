package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.services.SpecialtyService;
import com.tecsup.petclinic.util.TObjectCreator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Specialty Controller Mockito Test
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerMockitoTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialtyRepository specialtyRepository;

    @MockBean
    private SpecialtyService specialtyService;

    SpecialtyMapper mapper = Mappers.getMapper(SpecialtyMapper.class);

    @BeforeEach
    void setUp() {
        // Initialize RestAssured
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testFindAllSpecialties() throws Exception {

        int NRO_RECORD = 6;
        int ID_FIRST_RECORD = 1;

        List<SpecialtyDTO> specialtyTOs = TObjectCreator.getAllSpecialtyTOs();

        List<Specialty> specialties = this.mapper.toSpecialtyList(specialtyTOs);

        Mockito.when(specialtyService.findAll())
                .thenReturn(specialties);

        this.mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", is(NRO_RECORD)))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));

    }

    /**
     * Test find specialty by ID - OK case
     *
     * @throws Exception
     */
    @Test
    public void testFindSpecialtyOK() throws Exception {

        SpecialtyDTO specialtyTO = TObjectCreator.getSpecialtyTO();

        Specialty specialty = this.mapper.toSpecialty(specialtyTO);

        Mockito.when(specialtyService.findById(specialty.getId()))
                .thenReturn(specialty);

        mockMvc.perform(get("/specialties/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(specialtyTO.getId())))
                .andExpect(jsonPath("$.name", is(specialtyTO.getName())))
                .andExpect(jsonPath("$.office", is(specialtyTO.getOffice())))
                .andExpect(jsonPath("$.h_open", is(specialtyTO.getH_open())))
                .andExpect(jsonPath("$.h_close", is(specialtyTO.getH_close())));
    }

    /**
     * Test update specialty
     *
     * @throws Exception
     */
    @Test
    public void testUpdateSpecialty() throws Exception {

        SpecialtyDTO newSpecialtyTO = new SpecialtyDTO();
        newSpecialtyTO.setName("neurology");
        newSpecialtyTO.setOffice("Room 401");
        newSpecialtyTO.setH_open(8);
        newSpecialtyTO.setH_close(18);

        Specialty newSpecialty = this.mapper.toSpecialty(newSpecialtyTO);
        newSpecialty.setId(999); // id simulado

        Mockito.when(specialtyService.create(Mockito.any(Specialty.class)))
                .thenReturn(newSpecialty);

        // CREATE
        ResultActions mvcActions = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // UPDATE
        SpecialtyDTO upSpecialtyTO = new SpecialtyDTO();
        upSpecialtyTO.setId(id);
        upSpecialtyTO.setName("neurosurgery");
        upSpecialtyTO.setOffice("Room 402");
        upSpecialtyTO.setH_open(6);
        upSpecialtyTO.setH_close(20);

        Specialty upSpecialty = this.mapper.toSpecialty(upSpecialtyTO);
        upSpecialty.setId(id); // iguala id simulado

        Mockito.when(specialtyService.findById(id)).thenReturn(upSpecialty);
        Mockito.when(specialtyService.update(Mockito.any(Specialty.class)))
                .thenReturn(upSpecialty);

        mockMvc.perform(put("/specialties/" + id)
                        .content(om.writeValueAsString(upSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // DELETE
        Mockito.doNothing().when(this.specialtyService).delete(id);

        mockMvc.perform(delete("/specialties/" + id))
                .andExpect(status().isOk());
    }

}