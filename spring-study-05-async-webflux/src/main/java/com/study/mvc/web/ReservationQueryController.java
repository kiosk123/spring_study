package com.study.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

import com.study.mvc.Reservation;
import com.study.mvc.ReservationService;

import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() {
    }

    @PostMapping
    public String sumbitForm(ServerWebExchange serverWebExchange, Model model) {

        //스프링 MVC에서는 매개변수는 @RequestParam을 통해서 얻지만 웹 플럭스에서는 폼 데이터를 구성하는 매개변수 값은 가져올수 없고
        //URL에 포함된 매개변수값만 얻을 수 있다. 따라서 폼데이터를 얻고 매개변수값을 가져온 다음 서비스를 실행하려면 
        //serverWebExchange를 사용해야한다
        Flux<Reservation> reservations = serverWebExchange.getFormData()
                                            .map(form -> form.get("courtName"))
                                            .flatMapMany(Flux::fromIterable)
                                            .concatMap(courtName -> reservationService.query(courtName));
        model.addAttribute("reservations", reservations); //모델에 플럭스 데이터를 담는다.
        return "reservationQuery";
    }

}
