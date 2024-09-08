package org.cocktail.thenadlee.domain.validate;

import org.cocktail.thenadlee.common.error.ErrorCodeIfs;

public enum ErrorCodeCity implements ErrorCodeIfs {

    NULL_CITY(404,201,"요청한 지역의 정보가 없습니다."),
    EMPTY_CITY(500,202,"지역 조회되지않았습니다.")
            ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeCity(Integer httpStatusCode, Integer errorCode, String description) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.description = description;
    }


    @Override
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
