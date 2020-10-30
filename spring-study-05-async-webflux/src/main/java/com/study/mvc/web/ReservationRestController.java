package com.study.mvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.mvc.Reservation;
import com.study.mvc.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * 응답 결과가 하나면 Mono로 감싸고 여러개면 Flux로 감싼다.
     */
    @GetMapping
    public Flux<Reservation> listAll() {
        return reservationService.findAll();
    }

    @PostMapping
    public Flux<Reservation> find(@RequestBody Mono<ReservationQuery> query) {
        return query.flatMapMany(q -> reservationService.query(q.getCourtName()));
    }
}
