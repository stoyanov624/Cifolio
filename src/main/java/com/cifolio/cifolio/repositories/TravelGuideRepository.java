package com.cifolio.cifolio.repositories;

import com.cifolio.cifolio.models.city.TravelGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGuideRepository extends JpaRepository<TravelGuide, Long> {
}
