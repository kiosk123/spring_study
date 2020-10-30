package com.study.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping
    public String welcome(Model model) {
        //모델에 바로 객체를 넣는게 아닌 Mono를 이용해서 넣는다 동기면 즉시겠지만 비동기이기 때문이다.
        model.addAttribute("today", Mono.just(LocalDate.now()));
        return "welcome";
    }

}
