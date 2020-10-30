package com.study.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservationSuccess")
public class ReservationSuccessController {

    @GetMapping
    public void index() {}
}
