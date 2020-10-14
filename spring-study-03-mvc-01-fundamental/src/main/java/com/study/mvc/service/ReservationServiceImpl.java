package com.study.mvc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.study.mvc.vo.Player;
import com.study.mvc.vo.Reservation;
import com.study.mvc.vo.SportType;

@Service
public class ReservationServiceImpl implements ReservationService {
    
    public static final SportType TENNIS = new SportType(1, "Tennis");
    public static final SportType SOCCER = new SportType(2, "soccer");
    
    private final List<Reservation> reservations = new ArrayList<>();
    
    public ReservationServiceImpl() { 
        reservations.add(new Reservation("Tennis #1",java.sql.Date.valueOf(LocalDate.of(2008, 1, 14)) , 16,
                new Player("Roger", "N/A"), TENNIS));
        reservations.add(new Reservation("Tennis #2",java.sql.Date.valueOf(LocalDate.of(2008, 1, 14)) , 20,
                new Player("James", "N/A"), TENNIS));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getCourtName(), courtName))
                .collect(Collectors.toList());
    }
    
}
