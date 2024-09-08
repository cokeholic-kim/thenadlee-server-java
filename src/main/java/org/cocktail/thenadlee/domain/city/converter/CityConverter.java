package org.cocktail.thenadlee.domain.city.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cocktail.thenadlee.common.Converter;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.cocktail.thenadlee.domain.city.dao.CityResponse;
import org.cocktail.thenadlee.domain.city.dao.MonthInfoResponse;
import org.cocktail.thenadlee.domain.spot.dao.SpotResponse;

@Converter
@RequiredArgsConstructor
public class CityConverter {
    public CityResponse entityToResponse(CityEntity entity){
        return CityResponse.builder()
                .name(entity.getName())
                .lat(entity.getLat())
                .lon(entity.getLon())
                .build();
    }

    public List<SpotResponse> entityToSpotResponses(CityEntity city) {
        return city.getSpotPlaceList().stream().map(spot -> {
          return  SpotResponse.builder()
                    .spotName(spot.getName())
                    .description(spot.getDescription())
                    .imageUrl(spot.getImageUrl())
                    .lat(spot.getLat())
                    .lon(spot.getLon())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<MonthInfoResponse> entityToMonthlyInfoResponses(CityEntity city) {
        return city.getCityMonthlyInfoEntities().stream().map(month -> {
            return  MonthInfoResponse.builder()
                    .month(month.getMonth())
                    .description(month.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }
}
