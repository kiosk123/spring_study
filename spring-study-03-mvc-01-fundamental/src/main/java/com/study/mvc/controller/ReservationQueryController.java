package com.study.mvc.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.mvc.service.ReservationService;
import com.study.mvc.vo.Reservation;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    
    @Autowired
    private ReservationService reservationService;

    /**
     * 아래와 같이 URL 매핑이 되어 있지 않으면 요청 URL은 /reservationQuery과 동일하다
     * 반환 뷰 이름이 매핑 되지 않으면 요청 URL과 동일한 뷰이름이 반환된다.
     * 여기서는 reservationQuery이름의 뷰가 반환된다.
     */
    @GetMapping
    public void setupForm() {}
    
    @PostMapping
    public String submitForm(@RequestParam("courtName")String courtName, Model model) {
        Optional<String> opt = Optional.ofNullable(courtName);
        List<Reservation> reservations = Collections.emptyList();
        if (opt.isPresent()) {
            reservations = reservationService.query(opt.get());
        }
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }
}
