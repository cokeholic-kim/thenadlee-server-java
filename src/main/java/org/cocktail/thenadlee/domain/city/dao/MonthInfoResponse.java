package org.cocktail.thenadlee.domain.city.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MonthInfoResponse {
    private Integer month;
    private String description;
}
