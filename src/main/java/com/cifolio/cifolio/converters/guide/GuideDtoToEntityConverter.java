package com.cifolio.cifolio.converters.guide;

import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.mapper.city.CityMapper;
import com.cifolio.cifolio.model.city.TravelGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GuideDtoToEntityConverter implements Function<TravelGuideDto, TravelGuide> {
    private final CityMapper cityMapper;
    @Override
    public TravelGuide apply(TravelGuideDto guideDto) {
        return convertToEntity(guideDto);
    }

    private TravelGuide convertToEntity(TravelGuideDto guideDto) {
        return new TravelGuide(
                guideDto.getId(),
                guideDto.getName(),
                new HashSet(cityMapper.mapCityDtosToEntities(guideDto.getCities()))
        );
    }
}