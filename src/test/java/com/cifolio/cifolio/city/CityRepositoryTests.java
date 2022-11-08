package com.cifolio.cifolio.city;

import com.cifolio.cifolio.model.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CityRepositoryTests {

    @Autowired
    private CityRepository underTest;

    @Test
    void shouldFindCitiesByNameWhenTheyArePresentInDb() {
        City city = new City("Sofia", "someUrl");
        underTest.save(city);
        Pageable pagingData = PageRequest.of(Integer.parseInt(CityConstants.DEFAULT_PAGE), Integer.parseInt(CityConstants.DEFAULT_PAGE_SIZE));

        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", pagingData);
        assertThat(citiesPage).extracting("name").contains("Sofia");
    }

    @Test
    void shouldNotFindCitiesByNameWhenTheyAreNotPresentInDb() {
        Pageable pagingData = PageRequest.of(Integer.parseInt(CityConstants.DEFAULT_PAGE), Integer.parseInt(CityConstants.DEFAULT_PAGE_SIZE));
        Page<City> citiesPage = underTest.findAllOnPageByNameIsContaining("Sofia", pagingData);
        assertThat(citiesPage).extracting("name").doesNotContain("Sofia");
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
}