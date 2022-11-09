package com.cifolio.cifolio.city;
import com.cifolio.cifolio.model.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static com.cifolio.cifolio.city.CityConstants.DEFAULT_PAGING_DATA;
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
                new City(1L, "c1", "c1Url"),
                new City(2L, "c2", "c2Url")
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
        City city = new City("Sofia", "someUrl");

        given(cityRepository.findById(city.getId())).willReturn(Optional.of(city));
        given(cityRepository.save(any(City.class))).willReturn(mock(City.class));

        cityServiceUnderTest.updateCity(city);

        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository)
                .save(cityArgumentCaptor.capture());

        City capturedCity = cityArgumentCaptor.getValue();
        assertThat(city.getName()).isEqualTo(capturedCity.getName());
        assertThat(city.getPhoto()).isEqualTo(capturedCity.getPhoto());
    }

    @Test
    void updateCity_shouldThrowIllegalArgumentExceptionWhenCityNotFound() {
        City city = new City( "Sofia", "someUrl");

        assertThatThrownBy(() -> cityServiceUnderTest.updateCity(city))
                .isInstanceOf(IllegalArgumentException.class);

        verify(cityRepository, never()).save(any());
    }
}