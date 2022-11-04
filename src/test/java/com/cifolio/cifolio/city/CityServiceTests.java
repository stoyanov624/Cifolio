package com.cifolio.cifolio.city;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CityServiceTests {

    @Mock
    private CityRepository cityRepository;
    private CityService cityServiceUnderTest;

    @BeforeEach
    void setUp() {
        cityServiceUnderTest = new CityService(cityRepository);
    }

    @Test
    void shouldGetAllCitiesOnPage() {
        Pageable pagingData = PageRequest.of(Integer.parseInt(CityConstants.DEFAULT_PAGE), Integer.parseInt(CityConstants.DEFAULT_PAGE_SIZE));
        cityServiceUnderTest.getCitiesPage(pagingData);

        verify(cityRepository).findAll(pagingData);
    }

    @Test
    void shouldUpdateCity() {
        City city = new City( "Sofia", "someUrl");
        given(cityRepository.findById(any())).willReturn(Optional.of(city));

        cityServiceUnderTest.updateCity(city);

        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository)
                .save(cityArgumentCaptor.capture());

        City capturedCity = cityArgumentCaptor.getValue();
        assertThat(capturedCity).isEqualTo(city);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingCityWithoutId() {
        City city = new City( "Sofia", "someUrl");

        assertThatThrownBy(() -> cityServiceUnderTest.updateCity(city))
                .isInstanceOf(IllegalArgumentException.class);

        verify(cityRepository, never()).save(any());
    }
}