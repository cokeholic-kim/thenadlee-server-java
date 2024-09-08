package org.cocktail.thenadlee.domain.city.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import org.cocktail.thenadlee.common.exception.ApiException;
import org.cocktail.thenadlee.domain.city.business.CityBusiness;
import org.cocktail.thenadlee.domain.city.dao.CityResponse;
import org.cocktail.thenadlee.domain.city.dao.MonthInfoResponse;
import org.cocktail.thenadlee.domain.spot.dao.SpotResponse;
import org.cocktail.thenadlee.domain.validate.ErrorCodeCity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CityController.class)
//@AutoConfigureWebMvc
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CityBusiness cityBusiness;
    @Test
    @DisplayName("나라정보 조회")
    void getPlace() throws Exception {
        //given
        // given() Mock객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        String placeName = "singapore";
        given(cityBusiness.findCity(placeName)).willReturn(new CityResponse("singapore",1.3443533,103.2000868));
        //when
        mockMvc.perform(get("/place/" + placeName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.name").exists()) // $.필드이름
                .andExpect(jsonPath("$.body.lat").exists())
                .andExpect(jsonPath("$.body.lon").exists())
                .andExpect(jsonPath("$.result").exists())
                .andDo(print());
        //then : 메서드가 실행되었는지 검사
        verify(cityBusiness).findCity(placeName);
    }

    @Test
    @DisplayName("나라정보 조회 실패 - 도시를 찾을 수 없음")
    void getPlace_NotFound() throws Exception {
        // given
        String placeName = "unknown";
        given(cityBusiness.findCity(placeName)).willThrow(new ApiException(ErrorCodeCity.NULL_CITY));

        // when
        mockMvc.perform(get("/place/" + placeName))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.result.resultMessage").value("요청한 지역의 정보가 없습니다."))
                .andExpect(jsonPath("$.result.resultCode").value(201))
                .andDo(print());

        // then
        verify(cityBusiness, times(1)).findCity(placeName);
    }

    @Test
    @DisplayName("관광스팟 조회")
    void getSpotPlaces() throws Exception {
        //given
        // given() Mock객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        String placeName = "singapore";
        List<SpotResponse> list = Arrays.asList(
                new SpotResponse(49.3047044, -123.1480073,"testUrl","설명1","testSpot"),
                new SpotResponse(49.3047044, -123.1480073,"testUrl","설명2","testSpot"),
                new SpotResponse(49.3047044, -123.1480073,"testUrl","설명3","testSpot")
        );
        given(cityBusiness.findSpotList(placeName)).willReturn(list);
        //when
        mockMvc.perform(get("/place/spot/" + placeName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body[0].spotName").exists()) // $.필드이름
                .andExpect(jsonPath("$.body[0].lat").exists())
                .andExpect(jsonPath("$.body[0].lon").exists())
                .andExpect(jsonPath("$.body[0].description").exists())
                .andExpect(jsonPath("$.body[0].imageUrl").exists())
                .andDo(print());
        //then : 메서드가 실행되었는지 검사
        verify(cityBusiness).findSpotList(placeName);
    }

    @Test
    @DisplayName("관광스팟 조회 실패 - 도시를 찾을수 없음")
    void getSpotPlaces_NotFound() throws Exception {
        //given
        // given() Mock객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        String placeName = "unKnown";

        given(cityBusiness.findSpotList(placeName)).willThrow(new ApiException(ErrorCodeCity.NULL_CITY));
        //when
        mockMvc.perform(get("/place/spot/" + placeName))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.result.resultMessage").value("요청한 지역의 정보가 없습니다."))
                .andExpect(jsonPath("$.result.resultCode").value(201))
                .andDo(print());

        //then : 메서드가 실행되었는지 검사
        verify(cityBusiness).findSpotList(placeName);
    }

    @Test
    @DisplayName("월별관광정보 조회")
    void getMonthlyInfo() throws Exception {
        //given
        // given() Mock객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        String placeName = "singapore";
        List<MonthInfoResponse> list = Arrays.asList(
                new MonthInfoResponse(1,"description"),
                new MonthInfoResponse(2,"description"),
                new MonthInfoResponse(3,"description")
        );
        given(cityBusiness.findMonthInfo(placeName)).willReturn(list);
        //when
        mockMvc.perform(get("/place/month/" + placeName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body[0].month").exists()) // $.필드이름
                .andExpect(jsonPath("$.body[0].description").exists())
                .andDo(print());
        //then : 메서드가 실행되었는지 검사
        verify(cityBusiness).findMonthInfo(placeName);
    }

    @Test
    @DisplayName("월별관광정보 조회_실패")
    void getMonthlyInfo_NotFound() throws Exception {
        //given
        // given() Mock객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        String placeName = "unKnown";

        given(cityBusiness.findMonthInfo(placeName)).willThrow(new ApiException(ErrorCodeCity.NULL_CITY));
        //when
        mockMvc.perform(get("/place/month/" + placeName))
                .andExpect(status().isNotFound()) //404
                .andExpect(jsonPath("$.result.resultMessage").value("요청한 지역의 정보가 없습니다."))
                .andExpect(jsonPath("$.result.resultCode").value(201))
                .andDo(print());

        //then : 메서드가 실행되었는지 검사
        verify(cityBusiness).findMonthInfo(placeName);
    }
}