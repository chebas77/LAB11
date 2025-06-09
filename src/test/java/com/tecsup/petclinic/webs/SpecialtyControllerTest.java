package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Specialty Controller Integration Test
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllSpecialties() throws Exception {

        int ID_FIRST_RECORD = 1;

        this.mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }

    /**
     * Test find specialty by ID - OK case
     *
     * @throws Exception
     */
    @Test
    public void testFindSpecialtyOK() throws Exception {

        String SPECIALTY_NAME = "radiology";
        String SPECIALTY_OFFICE = "Farewell";
        int H_OPEN = 8;
        int H_CLOSE = 18;

        this.mockMvc.perform(get("/specialties/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(SPECIALTY_NAME)))
                .andExpect(jsonPath("$.office", is(SPECIALTY_OFFICE)))
                .andExpect(jsonPath("$.h_open", is(H_OPEN)))
                .andExpect(jsonPath("$.h_close", is(H_CLOSE)));
    }

    /**
     * Test update specialty
     *
     * @throws Exception
     */
    @Test
    public void testUpdateSpecialty() throws Exception {

        String SPECIALTY_NAME = "neurology";
        String SPECIALTY_OFFICE = "Room 401";
        int H_OPEN = 8;
        int H_CLOSE = 18;

        String UP_SPECIALTY_NAME = "neurosurgery";
        String UP_SPECIALTY_OFFICE = "Room 402";
        int UP_H_OPEN = 6;
        int UP_H_CLOSE = 20;

        SpecialtyDTO newSpecialtyTO = new SpecialtyDTO();
        newSpecialtyTO.setName(SPECIALTY_NAME);
        newSpecialtyTO.setOffice(SPECIALTY_OFFICE);
        newSpecialtyTO.setH_open(H_OPEN);
        newSpecialtyTO.setH_close(H_CLOSE);

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
        upSpecialtyTO.setName(UP_SPECIALTY_NAME);
        upSpecialtyTO.setOffice(UP_SPECIALTY_OFFICE);
        upSpecialtyTO.setH_open(UP_H_OPEN);
        upSpecialtyTO.setH_close(UP_H_CLOSE);

        mockMvc.perform(put("/specialties/" + id)
                        .content(om.writeValueAsString(upSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // FIND
        mockMvc.perform(get("/specialties/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.name", is(UP_SPECIALTY_NAME)))
                .andExpect(jsonPath("$.office", is(UP_SPECIALTY_OFFICE)))
                .andExpect(jsonPath("$.h_open", is(UP_H_OPEN)))
                .andExpect(jsonPath("$.h_close", is(UP_H_CLOSE)));

        // DELETE
        mockMvc.perform(delete("/specialties/" + id))
                .andExpect(status().isOk());
    }

}