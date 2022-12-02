package com.cifolio.cifolio.service;

import com.cifolio.cifolio.models.city.City;
import com.cifolio.cifolio.repositories.CityRepository;
import com.cifolio.cifolio.service.city.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.cifolio.cifolio.constants.CityConstants.DEFAULT_PAGING_DATA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTests {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityServiceUnderTest;

    @Test
    void getCitiesPage() {
        Page<City> cityPage = new PageImpl<>(List.of(
                City.builder().id(1L).name("firstCityName").photo("firstCityUrl").build(),
                City.builder().id(2L).name("secondCityName").photo("firstCityUrl").build()
        ));

        given(cityRepository.findAll(DEFAULT_PAGING_DATA)).willReturn(cityPage);
        Page<City> fetchedCities = cityServiceUnderTest.getCitiesPage("", DEFAULT_PAGING_DATA);
        verify(cityRepository).findAll(DEFAULT_PAGING_DATA);

        assertThat(cityPage.getContent().size() == fetchedCities.getContent().size());

        for (int i = 0; i < cityPage.getContent().size(); i++) {
            assertThat(cityPage.getContent().get(i).getName() == fetchedCities.getContent().get(i).getName());
            assertThat(cityPage.getContent().get(i).getPhoto() == fetchedCities.getContent().get(i).getPhoto());
        }
    }

    @Test
    void updateCity() {
        Long id = 1L;
        City newCity = City.builder().id(id).name("NewCityName").photo("someUrl").build();
        City existingCity = City.builder().id(id).name("ExistingCityName").photo("someUrl").build();

        given(cityRepository.findById(id)).willReturn(Optional.of(existingCity));
        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        given(cityRepository.save(cityArgumentCaptor.capture())).willReturn(newCity);

        cityServiceUnderTest.updateCity(newCity);
        City capturedCity = cityArgumentCaptor.getValue();

        verify(cityRepository, times(1)).save(capturedCity);

        assertThat(capturedCity).isNotNull();
        assertThat(newCity.getName()).isEqualTo(capturedCity.getName());
        assertThat(newCity.getPhoto()).isEqualTo(capturedCity.getPhoto());
    }

    @Test
    void updateCity_shouldThrowEntityNotFoundExceptionWhenCityNotFound() {
        City city = City.builder().name("Sofia").photo("someUrl").build();
        assertThatThrownBy(() -> cityServiceUnderTest.updateCity(city))
                .isInstanceOf(EntityNotFoundException.class);

        verify(cityRepository, never()).save(any());
    }
}