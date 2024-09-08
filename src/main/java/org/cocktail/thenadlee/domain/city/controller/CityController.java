package org.cocktail.thenadlee.domain.city.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.thenadlee.common.api.Api;
import org.cocktail.thenadlee.domain.city.business.CityBusiness;
import org.cocktail.thenadlee.domain.city.dao.CityResponse;
import org.cocktail.thenadlee.domain.city.dao.MonthInfoResponse;
import org.cocktail.thenadlee.domain.spot.dao.SpotResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("place")
@RequiredArgsConstructor
public class CityController {
    private final CityBusiness cityBusiness;
    // 나라정보 받아오기 /place/:place
    @GetMapping("{placeName}")
    public Api<CityResponse> getPlace(@PathVariable("placeName") String placeName) {
        CityResponse city = cityBusiness.findCity(placeName);
        return Api.OK(city);
    }

    // 추천 관광지 받아오기 "/recommend/:place"
    @GetMapping("spot/{placeName}")
    public Api<List<SpotResponse>> getSpotPlaces(@PathVariable("placeName") String placeName) {
        List<SpotResponse> city = cityBusiness.findSpotList(placeName);
        return Api.OK(city);
    }

    // 나라의 월별정보
    @GetMapping("month/{placeName}")
    public Api<List<MonthInfoResponse>> getMonthInfo(@PathVariable String placeName){
        List<MonthInfoResponse> monthInfo = cityBusiness.findMonthInfo(placeName);
        return Api.OK(monthInfo);
    }
}
