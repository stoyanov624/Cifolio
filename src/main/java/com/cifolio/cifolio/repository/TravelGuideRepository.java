package com.cifolio.cifolio.repository;

import com.cifolio.cifolio.model.city.TravelGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGuideRepository extends JpaRepository<TravelGuide, Long> {
}
