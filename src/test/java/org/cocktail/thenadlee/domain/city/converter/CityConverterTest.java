package org.cocktail.thenadlee.domain.city.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import org.cocktail.thenadlee.db.city.CityEntity;
import org.cocktail.thenadlee.db.spotplace.SpotPlaceEntity;
import org.cocktail.thenadlee.domain.city.dao.CityResponse;
import org.cocktail.thenadlee.domain.spot.dao.SpotResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityConverterTest {
    private CityConverter cityConverter;
    private CityEntity cityEntity;
    private SpotPlaceEntity spotEntity1;
    private SpotPlaceEntity spotEntity2;

    @BeforeEach
    void setUp() {
        cityConverter = new CityConverter();

        spotEntity1 = new SpotPlaceEntity();
        spotEntity1.setName("스탠리 공원");
        spotEntity1.setDescription("해변, 산책로, 가족 명소, 아름다운 방파제가 있는 밴쿠버 최대 규모의 도심 공원입니다.");
        spotEntity1.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipOzLdZQ_XM8wIH3c9uH6OaKNQpDiNjGxqrrhHl5=w408-h307-k-no");
        spotEntity1.setCoordinate("49.3047044,-123.1480073");

        spotEntity2 = new SpotPlaceEntity();
        spotEntity2.setName("그랜빌 아일랜드");
        spotEntity2.setDescription("작은 페리로 시내와 연결되는 그랜빌 아일랜드는 농산물과 토르티야, 카레 등의 포장 음식을 판매하는 노점이 있는 그랜빌 아일랜드 퍼블릭 마켓으로 유명한 팔스 크리크의 번화한 반도입니다.");
        spotEntity2.setImageUrl("https://lh5.googleusercontent.com/p/AF1QipMTLdTFj2ETUJrHIgtxAxeRsLOgJ0GnEOEIfLSD=w425-h240-k-no");
        spotEntity2.setCoordinate("49.3047044,-123.1480073");


        cityEntity = new CityEntity();
        cityEntity.setName("Vancouver");
        cityEntity.setCoordinate("49.3047044,-123.1480073");
        cityEntity.setSpotPlaceList(Arrays.asList(spotEntity1, spotEntity2));
    }

    @Test
    void testEntityToResponse() {
        CityResponse response = cityConverter.entityToResponse(cityEntity);

        assertNotNull(response);
        assertEquals("Vancouver", response.getName());
        assertEquals(49.3047044, response.getLat());
        assertEquals(-123.1480073, response.getLon());
    }

    @Test
    void testEntityToSpotResponses() {
        List<SpotResponse> spotResponses = cityConverter.entityToSpotResponses(cityEntity);

        assertNotNull(spotResponses);
        assertEquals(2, spotResponses.size());

        SpotResponse spotResponse1 = spotResponses.get(0);
        assertEquals("스탠리 공원", spotResponse1.getSpotName());
        assertEquals("해변, 산책로, 가족 명소, 아름다운 방파제가 있는 밴쿠버 최대 규모의 도심 공원입니다.", spotResponse1.getDescription());
        assertEquals("https://lh5.googleusercontent.com/p/AF1QipOzLdZQ_XM8wIH3c9uH6OaKNQpDiNjGxqrrhHl5=w408-h307-k-no", spotResponse1.getImageUrl());
        assertEquals(49.3047044, spotResponse1.getLat());
        assertEquals(-123.1480073, spotResponse1.getLon());

        SpotResponse spotResponse2 = spotResponses.get(1);
        assertEquals("그랜빌 아일랜드", spotResponse2.getSpotName());
        assertEquals("작은 페리로 시내와 연결되는 그랜빌 아일랜드는 농산물과 토르티야, 카레 등의 포장 음식을 판매하는 노점이 있는 그랜빌 아일랜드 퍼블릭 마켓으로 유명한 팔스 크리크의 번화한 반도입니다.", spotResponse2.getDescription());
        assertEquals("https://lh5.googleusercontent.com/p/AF1QipMTLdTFj2ETUJrHIgtxAxeRsLOgJ0GnEOEIfLSD=w425-h240-k-no", spotResponse2.getImageUrl());
        assertEquals(49.3047044, spotResponse2.getLat());
        assertEquals(-123.1480073, spotResponse2.getLon());
    }
}