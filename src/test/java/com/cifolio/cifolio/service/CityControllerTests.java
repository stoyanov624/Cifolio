package com.cifolio.cifolio.service;

import com.cifolio.cifolio.controllers.CityController;
import com.cifolio.cifolio.converters.city.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.city.CityEntityToDtoConverter;
import com.cifolio.cifolio.dtos.city.CityDto;
import com.cifolio.cifolio.exception_handling.exceptions.EntityNotFoundException;
import com.cifolio.cifolio.mappers.city.CityMapper;
import com.cifolio.cifolio.models.city.City;
import com.cifolio.cifolio.service.city.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CityControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CityService cityService;
    @MockBean
    CityDtoToEntityConverter cityDtoToEntityConverter;
    @MockBean
    CityEntityToDtoConverter cityEntityToDtoConverter;
    @MockBean
    CityMapper cityMapper;

    @Test
    void putCity_shouldSendBadRequestStatusWhenNotFound() throws Exception {
        CityDto city = new CityDto(null, "Sofia", "url");

        City cityModel = cityDtoToEntityConverter.apply(city);
        doThrow(EntityNotFoundException.class).when(cityService).updateCity(cityModel);
        String input = objectMapper.writeValueAsString(city);

        mockMvc.perform(put("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andExpect(status().isNotFound());
    }
}
