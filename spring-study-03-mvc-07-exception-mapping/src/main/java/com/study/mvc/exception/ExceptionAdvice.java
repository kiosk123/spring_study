package com.study.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 범용적으로 처리할 예외는 별도 클래스로 빼내어 다음과 같이 처리한다.
 * 애플리케이션 컨텍스트에 존재하는 모든 컨트롤러에서 @ControllerAdvice통해 선언된 예외가 
 * 감지될 경우 @ControllerAdvice에서 선언된 핸들러에서 처리된다.
 * 
 */
@ControllerAdvice
public class ExceptionAdvice {
    
    @ExceptionHandler(ReservationNotAvailableException.class)
    public String handle(ReservationNotAvailableException ex) {
        return "reservationNotAvailable";
    }

    @ExceptionHandler
    public String handleDefault(Exception e) {
        return "error";
    }
}
