package com.study.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.mvc.exception.ReservationNotAvailableException;
import com.study.mvc.service.ReservationService;
import com.study.mvc.vo.Player;
import com.study.mvc.vo.Reservation;
import com.study.mvc.vo.ReservationValidator;
import com.study.mvc.vo.SportType;


@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

    private final ReservationService reservationService;
    private final ReservationValidator validator;

    public ReservationFormController(ReservationService reservationService,
                                     ReservationValidator validator) {
        this.reservationService = reservationService;
        this.validator = validator;
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
            @ModelAttribute("reservation") Reservation reservation,
            BindingResult result, SessionStatus status) {

        validator.validate(reservation, result);
        if (result.hasErrors()) {
            return "reservationForm";
        } else {
            reservationService.make(reservation);
            status.setComplete();

            return "redirect:reservationSuccess";
        }
    }

    /**
     * @ExceptionHandler로 예외 매핑한다 작동은 @RequestMapping과 비슷하다
     * @ExceptionHandler는 자신을 둘러싼 컨트롤러에서만 작동한다.
     * 여기서는 컨트롤러에서 ReservationNotAvailableException 예외 발생시
     * reservationNotAvailable 논리뷰를 반환한다.
     * 
     * 논리뷰 뿐만 아니라 ModelAndView, View등의 객체되 반환가능하다.
     */
    @ExceptionHandler(ReservationNotAvailableException.class)
    public String handle(ReservationNotAvailableException ex) {
        return "reservationNotAvailable";
    }

    @ExceptionHandler
    public String handleDefault(Exception e) {
        return "error";
    }
}
