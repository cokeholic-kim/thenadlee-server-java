package org.cocktail.thenadlee.domain.city.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.cocktail.thenadlee.domain.city.converter.CityConverter;
import org.cocktail.thenadlee.domain.city.dao.CityResponse;
import org.cocktail.thenadlee.domain.city.dao.MonthInfoResponse;
import org.cocktail.thenadlee.domain.city.service.CityService;
import org.cocktail.thenadlee.domain.spot.dao.SpotResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityBusiness {
    private final CityService cityService;
    private final CityConverter cityConverter;

    public CityResponse findCity(String place){
        CityEntity city = cityService.findCity(place);
        return cityConverter.entityToResponse(city);
    }

    public List<SpotResponse> findSpotList(String placeName) {
        CityEntity city = cityService.findCity(placeName);
        return cityConverter.entityToSpotResponses(city);
    }

    public List<MonthInfoResponse> findMonthInfo(String placeName) {
        CityEntity city = cityService.findCity(placeName);
        return cityConverter.entityToMonthlyInfoResponses(city);

    }

}
