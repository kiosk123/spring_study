package com.study.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.mvc.service.ReservationService;
import com.study.mvc.vo.Player;
import com.study.mvc.vo.Reservation;
import com.study.mvc.vo.ReservationValidator;
import com.study.mvc.vo.SportType;

//@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationValidationFormController {

    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public ReservationValidationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }
    
    /**
     *  밸리데이션 검증기를 등록하는 함수다
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }

    @ModelAttribute("sportTypes")
    public List<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(
            @RequestParam(required = false, value = "username") String username,
            Model model) {

        Reservation reservation = new Reservation();
        reservation.setPlayer(new Player(username, null));

        model.addAttribute("reservation", reservation);

        return "reservationForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(
            //밸리데이션 구현 클래스가 적용되는 경우 @Validated 애너테이션을 붙여준다
            //@Validated가 밸리데이션 검증기에 의한 검증대상 객체임을 알려준다
            @ModelAttribute("reservation") @Validated Reservation reservation,
            BindingResult result, SessionStatus status) {
            
        if (result.hasErrors()) {
            return "reservationForm";
        } else {
            reservationService.make(reservation);
            status.setComplete(); //세션만료함수
            return "redirect:reservationSuccess";
        }
    }
}