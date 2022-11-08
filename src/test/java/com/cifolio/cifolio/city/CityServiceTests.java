package com.cifolio.cifolio.city;

import com.cifolio.cifolio.dtos.CityDto;
import com.cifolio.cifolio.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.cifolio.cifolio.city.CityConstants.DEFAULT_PAGING_DATA;
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
        Pageable pagingData = PageRequest.of(CityConstants.DEFAULT_PAGE, CityConstants.DEFAULT_PAGE_SIZE);
        cityServiceUnderTest.getCitiesPage("", DEFAULT_PAGING_DATA);

        verify(cityRepository).findAll(pagingData);
    }

    @Test
    void shouldUpdateCity() {
        CityDto city = new CityDto( "Sofia", "someUrl");
        City existingCity = new City("Sofia2", "someurl");
        given(cityRepository.findById(any())).willReturn(Optional.of(existingCity));

        cityServiceUnderTest.updateCity(city);

        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository)
                .save(cityArgumentCaptor.capture());

        City capturedCity = cityArgumentCaptor.getValue();
        assertThat(capturedCity).isEqualTo(city);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingCityWithoutId() {
        CityDto city = new CityDto( "Sofia", "someUrl");

        assertThatThrownBy(() -> cityServiceUnderTest.updateCity(city))
                .isInstanceOf(IllegalArgumentException.class);

        verify(cityRepository, never()).save(any());
    }
}