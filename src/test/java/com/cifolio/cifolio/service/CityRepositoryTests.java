package com.cifolio.cifolio.service;

import com.cifolio.cifolio.model.City;
import com.cifolio.cifolio.repository.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import static com.cifolio.cifolio.constants.CityConstants.DEFAULT_PAGING_DATA;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CityRepositoryTests {

    @Autowired
    private CityRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindCitiesByNameWhenTheyArePresentInDb() {
        City city = new City("Sofia", "someUrl");
        underTest.save(city);

        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", DEFAULT_PAGING_DATA);
        assertThat(citiesPage).extracting("name").contains("Sofia");
    }

    @Test
    void shouldNotFindCitiesByNameWhenTheyAreNotPresentInDb() {
        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", DEFAULT_PAGING_DATA);
        assertThat(citiesPage).extracting("name").doesNotContain("Sofia");
    }
}