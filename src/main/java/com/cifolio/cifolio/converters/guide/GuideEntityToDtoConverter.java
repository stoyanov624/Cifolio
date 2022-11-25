package com.cifolio.cifolio.converters.guide;

import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.mapper.city.CityMapper;
import com.cifolio.cifolio.model.city.TravelGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GuideEntityToDtoConverter implements Function<TravelGuide, TravelGuideDto> {
    private final CityMapper cityMapper;
    @Override
    public TravelGuideDto apply(TravelGuide city) {
        return convertToDto(city);
    }

    private TravelGuideDto convertToDto(TravelGuide guide) {
        return new TravelGuideDto(
                guide.getId(),
                guide.getName(),
                cityMapper.mapCityEntitiesToDtos(guide.getCities())
        );
    }
}