package org.cocktail.thenadlee.domain.validate;

import java.util.Optional;
import org.cocktail.thenadlee.common.error.ErrorCodeIfs;
import org.cocktail.thenadlee.common.exception.ApiException;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityValidator {

    public CityEntity validateOptionalCity(Optional<CityEntity> cityEntity){
        if(cityEntity.isEmpty()){
            throw new ApiException(ErrorCodeCity.NULL_CITY);
        }
        return cityEntity.get();
    }
}
