package com.cifolio.cifolio.converters.guide;

import com.cifolio.cifolio.dtos.guide.TravelGuideDto;
import com.cifolio.cifolio.mappers.city.CityMapper;
import com.cifolio.cifolio.models.city.TravelGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GuideEntityToDtoConverter implements Function<TravelGuide, TravelGuideDto> {
    private final CityMapper cityMapper;
    @Override
    public TravelGuideDto apply(TravelGuide travelGuide) {
        return convertToDto(travelGuide);
    }

    private TravelGuideDto convertToDto(TravelGuide guide) {
        return new TravelGuideDto(
                guide.getId(),
                guide.getName(),
                cityMapper.mapToDtoList(guide.getCities())
        );
    }
}