package com.study.mvc.vo;

import org.springframework.core.convert.converter.Converter;

import com.study.mvc.service.ReservationService;

/**
 * select 폼에서 전송되는 프로퍼티를 커스텀 클래스로 바인딩처리
 * 구성클래스(CourtConfiguration)에 컨버터를 등록한다.
 * 왜냐하면 sportType으로 전송되는 것은 id프로퍼티값 하나이기 때문에 매핑이 필요하다
 */
public class SportTypeConverter implements Converter<String, SportType> {

    private ReservationService reservationService;

    public SportTypeConverter(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public SportType convert(String source) {
        int sportTypeId = Integer.parseInt(source);
        SportType sportType = reservationService.getSportType(sportTypeId);
        return sportType;
    }
}
