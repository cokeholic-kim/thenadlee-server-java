package org.cocktail.thenadlee.domain.city.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.cocktail.thenadlee.db.city.CityRepository;
import org.cocktail.thenadlee.domain.validate.CityValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityValidator validator;

    public CityEntity findCity(String cityName){
        Optional<CityEntity> optionalCity = cityRepository.findByName(cityName);
        return validator.validateOptionalCity(optionalCity);
    }
}
