package org.cocktail.thenadlee.domain.city.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.cocktail.thenadlee.common.exception.ApiException;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.cocktail.thenadlee.db.city.CityRepository;
import org.cocktail.thenadlee.domain.validate.CityValidator;
import org.cocktail.thenadlee.domain.validate.ErrorCodeCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityValidator validator;

    @InjectMocks
    private CityService cityService;

    private CityEntity cityEntity;

    @BeforeEach
    void setUp() {
        cityEntity = new CityEntity();
        cityEntity.setName("Seoul");
    }

    @Test
    void testFindCity_Success() {
        //seoul city 가 존재하는경우
        when(cityRepository.findByName("Seoul")).thenReturn(Optional.of(cityEntity));
        // optional city가 null 이 아니고 entity를 리턴하는경우
        when(validator.validateOptionalCity(Optional.of(cityEntity))).thenReturn(cityEntity);

        // cityService의 findcity메서드를 통해서 repository.findbyName을 실행 한후 validator를 거쳐서 entity를 정상적으로 리턴
        CityEntity result = cityService.findCity("Seoul");

        assertNotNull(result);
        assertEquals("Seoul", result.getName());
        verify(cityRepository, times(1)).findByName("Seoul"); //메서드가 한번만 사용되는지검사
        verify(validator, times(1)).validateOptionalCity(Optional.of(cityEntity));
    }

    @Test
    void testFindCity_NotFound() {
        when(cityRepository.findByName("Unknown")).thenReturn(Optional.empty());
        when(validator.validateOptionalCity(Optional.empty())).thenThrow(new ApiException(ErrorCodeCity.NULL_CITY));

        Exception exception = assertThrows(ApiException.class, () -> {
            cityService.findCity("Unknown");
        });

        assertEquals("요청한 지역의 정보가 없습니다.", exception.getMessage());
        verify(cityRepository, times(1)).findByName("Unknown");
        verify(validator, times(1)).validateOptionalCity(Optional.empty());
    }
}
