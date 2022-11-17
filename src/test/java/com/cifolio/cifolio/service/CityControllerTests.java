package com.cifolio.cifolio.service;

import com.cifolio.cifolio.controller.CityController;
import com.cifolio.cifolio.converters.CityDtoToEntityConverter;
import com.cifolio.cifolio.converters.CityEntityToDtoConverter;
import com.cifolio.cifolio.dto.CityDto;
import com.cifolio.cifolio.model.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
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

    @Test
    void putCity_shouldSendBadRequestStatusWhenNotFound() throws Exception {
        CityDto city = new CityDto(null, "Sofia", "url");

        City cityModel = cityDtoToEntityConverter.apply(city);
        doThrow(IllegalArgumentException.class).when(cityService).updateCity(cityModel);
        String input = objectMapper.writeValueAsString(city);

        mockMvc.perform(put("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andExpect(status().isBadRequest());
    }
}
