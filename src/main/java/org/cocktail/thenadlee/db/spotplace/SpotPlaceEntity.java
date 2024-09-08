package org.cocktail.thenadlee.db.spotplace;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.thenadlee.db.BaseEntity;
import org.cocktail.thenadlee.db.city.CityEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "spot_place")
public class SpotPlaceEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
    private String imageUrl;
    private String coordinate;
    private String description;
    private String name;

    public Double getLat(){
        String[] split = coordinate.split(",");
        return Double.parseDouble(split[0]);
    }

    public Double getLon(){
        String[] split = coordinate.split(",");
        return Double.parseDouble(split[1]);
    }
}
