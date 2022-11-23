package com.cifolio.cifolio.mapper.guide;

import com.cifolio.cifolio.converters.guide.GuideEntityToDtoConverter;
import com.cifolio.cifolio.dto.guide.TravelGuideDto;
import com.cifolio.cifolio.model.city.TravelGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GuideMapper {
    private final GuideEntityToDtoConverter guideEntityToDtoConverter;

    public List<TravelGuideDto> mapGuideEntitiesToGuideDtos(List<TravelGuide> travelGuides) {
        return travelGuides
                .stream()
                .map(guideEntityToDtoConverter)
                .collect(Collectors.toList());
    }
}
