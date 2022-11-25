package com.cifolio.cifolio.converters.guide;

import com.cifolio.cifolio.dto.guide.TravelGuideGeneralInformationDto;
import com.cifolio.cifolio.model.city.TravelGuide;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GuideEntityToDtoConverter implements Function<TravelGuide, TravelGuideGeneralInformationDto> {
    @Override
    public TravelGuideGeneralInformationDto apply(TravelGuide city) {
        return convertToDto(city);
    }

    private TravelGuideGeneralInformationDto convertToDto(TravelGuide guide) {
        return new TravelGuideGeneralInformationDto(
                guide.getId(),
                guide.getName()
        );
    }
}