package com.cifolio.cifolio.mapper.guide;

import com.cifolio.cifolio.converters.guide.GuideDtoToEntityConverter;
import com.cifolio.cifolio.converters.guide.GuideEntityToDtoConverter;
import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.model.city.TravelGuide;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GuideMapper {
    private final GuideEntityToDtoConverter guideEntityToDtoConverter;
    private final GuideDtoToEntityConverter guideDtoToEntityConverter;

    public List<TravelGuideDto> mapGuideEntitiesToGuideDto(List<TravelGuide> travelGuides) {
        return travelGuides
                .stream()
                .map(guideEntityToDtoConverter)
                .collect(Collectors.toList());
    }

    public TravelGuide mapGuideDtoToEntity(TravelGuideDto guideDto) {
        return guideDtoToEntityConverter.apply(guideDto);
    }
}
