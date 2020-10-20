package com.study.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.study.mvc.service.ReservationService;
import com.study.mvc.vo.SportTypeConverter;

@Configuration
@ComponentScan("com.study.mvc")
@EnableWebMvc
public class CourtConfiguration implements WebMvcConfigurer{
    
    @Autowired
    private ReservationService reservationService;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SportTypeConverter(reservationService));
    }
}
