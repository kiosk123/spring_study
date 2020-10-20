package com.study.mvc.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.study.mvc.domain.PeriodicReservation;
import com.study.mvc.domain.PeriodicReservationValidator;
import com.study.mvc.domain.Player;
import com.study.mvc.service.ReservationService;


@Controller
@RequestMapping("/periodicReservationForm")
// 모든 폼은 modelAttribute="reservation"에 바인딩되므로 데이터가 유실되지 않게 유저 세션에 보관함
@SessionAttributes("reservation") 
public class PeriodicReservationController {

    //페이지 번호와 뷰 이름 매핑을 위함 해쉬맵
    private final Map<Integer, String> pageForms = new HashMap<>(3);
    
    private final ReservationService reservationService;
    private final PeriodicReservationValidator validator;

    public PeriodicReservationController(ReservationService reservationService,
                                         PeriodicReservationValidator periodicReservationValidator) {
        this.reservationService = reservationService;
        this.validator = periodicReservationValidator;
    }

    @PostConstruct
    public void initialize() {
        pageForms.put(0, "reservationCourtForm");
        pageForms.put(1, "reservationTimeForm");
        pageForms.put(2, "reservationPlayerForm");
    }
    
    /**
     * 모든 반환 뷰가 참조하는 periods가 바인딩 된 데이터를 반환하는 메소드
     */
    @ModelAttribute("periods")
    public Map<Integer, String> periods() {
        Map<Integer, String> periods = new HashMap<>();
        periods.put(1, "Daily");
        periods.put(7, "Weekly");
        return periods;
    }

    @GetMapping
    public String setupForm(Model model) {
        PeriodicReservation reservation = new PeriodicReservation();
        reservation.setPlayer(new Player());
        model.addAttribute("reservation", reservation);

        return "reservationCourtForm";
    }

    /**
     * _cancel이 들어오면 현제 페이지에서 입력된 모든 값을 취소(초기화)하고 머문다.
     */
    @PostMapping(params = {"_cancel"})
    public String cancelForm(
            @ModelAttribute("reservation") PeriodicReservation reservation, BindingResult result,
            @RequestParam("_page") int currentPage) {
        return pageForms.get(currentPage);

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
    }

    /**
     * 마법사 폼에서 유저가 finish 버튼을 클릭할때 실행
     */
    @PostMapping(params = {"_finish"})
    public String completeForm(
            @Validated @ModelAttribute("reservation") PeriodicReservation reservation,
            BindingResult result, SessionStatus status, 
            @RequestParam("_page") int currentPage, HttpServletRequest request) {
        if (!result.hasErrors()) {
            reservationService.makePeriodic(reservation);
            status.setComplete();
            return "redirect:reservationSuccess";
        } else {
            return pageForms.get(currentPage);
        }
    }

    @PostMapping
    public String submitForm(
            HttpServletRequest request,
            @ModelAttribute("reservation") PeriodicReservation reservation,
            BindingResult result, @RequestParam("_page") int currentPage) {

        //이동한 페이지를 가져옴
        int targetPage = getTargetPage(request, "_target", currentPage);

        //이전 페이지로 이동
        if (targetPage < currentPage) {
            return pageForms.get(targetPage);
        }

        //현재 페이지 검증 처리
        validateCurrentPage(reservation, result, currentPage);
        if (!result.hasErrors()) {
            return pageForms.get(targetPage);
        } else {
            return pageForms.get(currentPage);
        }
    }

    private void validateCurrentPage(PeriodicReservation reservation, BindingResult result, int currentPage) {
        switch (currentPage) {
            case 0:
                validator.validateCourt(reservation, result);
                break;
            case 1:
                validator.validateTime(reservation, result);
                break;
            case 2:
                validator.validatePlayer(reservation, result);
                break;
        }
    }

    private int getTargetPage(HttpServletRequest request, String paramPrefix, int currentPage) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith(paramPrefix)) {
                for (int i = 0; i < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; i++) {
                    String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[i];
                    if (paramName.endsWith(suffix)) {
                        paramName = paramName.substring(0, paramName.length() - suffix.length());
                    }
                }
                return Integer.parseInt(paramName.substring(paramPrefix.length()));
            }
        }
        return currentPage;
    }
}
