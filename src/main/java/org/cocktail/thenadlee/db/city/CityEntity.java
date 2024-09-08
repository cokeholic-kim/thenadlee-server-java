package org.cocktail.thenadlee.db.city;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.thenadlee.db.BaseEntity;
import org.cocktail.thenadlee.db.citymonthlyinfo.CityMonthlyInfoEntity;
import org.cocktail.thenadlee.db.spotplace.SpotPlaceEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {
    private String name;
    private String coordinate;
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpotPlaceEntity> spotPlaceList;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CityMonthlyInfoEntity> cityMonthlyInfoEntities;

    public Double getLat(){
        String[] split = coordinate.split(",");
        return Double.parseDouble(split[0]);
    }

    public Double getLon(){
        String[] split = coordinate.split(",");
        return Double.parseDouble(split[1]);
    }
}
