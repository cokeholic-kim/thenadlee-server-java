package org.cocktail.thenadlee.domain.spot.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SpotResponse {
    private Double lat;
    private Double lon;
    private String imageUrl;
    private String description;
    private String spotName;
}
