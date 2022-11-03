package com.cifolio.cifolio.city;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CityRepositoryTests {

    @Autowired
    private CityRepository underTest;

    @Test
    void shouldFindCitiesByNameWhenTheyArePresentInDb() {
        final int page = 0;
        final int elementsOnPage = 12;
        City city = new City("Sofia", "someUrl");
        underTest.save(city);

        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", PageRequest.of(page, elementsOnPage));
        assertThat(citiesPage).extracting("name").contains("Sofia");
    }

    @Test
    void shouldNotFindCitiesByNameWhenTheyAreNotPresentInDb() {
        final int page = 0;
        final int elementsOnPage = 12;

        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", PageRequest.of(page, elementsOnPage));
        assertThat(citiesPage).extracting("name").doesNotContain("Sofia");
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
}