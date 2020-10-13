package com.study.mvc.service;

import java.util.List;

import com.study.mvc.vo.Reservation;

public interface ReservationService {
    public List<Reservation> query(String courtName);
}
