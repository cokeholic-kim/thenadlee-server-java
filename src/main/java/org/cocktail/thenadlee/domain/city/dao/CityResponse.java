package org.cocktail.thenadlee.domain.city.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CityResponse {
    private String name;
    private Double lat;
    private Double lon;
}
