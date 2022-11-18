package com.cifolio.cifolio.repository;

import com.cifolio.cifolio.model.city.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAllOnPageByNameIsContaining(String name, Pageable pagingData);
}
