package org.cocktail.thenadlee.db.recommendPosts;

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
@Table(name = "recommend_posts")
public class RecommendPostsEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
    private String postUrl;
}
