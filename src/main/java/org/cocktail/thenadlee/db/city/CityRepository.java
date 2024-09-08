package org.cocktail.thenadlee.db.city;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
    Optional<CityEntity> findByName(String name);
}
