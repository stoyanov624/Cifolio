package com.cifolio.cifolio.converters.guide;

import com.cifolio.cifolio.dtos.guide.TravelGuideDto;
import com.cifolio.cifolio.mappers.city.CityMapper;
import com.cifolio.cifolio.models.city.TravelGuide;
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
        return new TravelGuide(guideDto.getId(), guideDto.getName(), new HashSet(cityMapper.toEntityList(guideDto.getCities())));
    }
}