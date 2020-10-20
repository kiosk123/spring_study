package com.study.mvc.service;

import java.time.LocalDate;
import java.util.List;

import com.study.mvc.exception.ReservationNotAvailableException;
import com.study.mvc.vo.PeriodicReservation;
import com.study.mvc.vo.Reservation;
import com.study.mvc.vo.SportType;

public interface ReservationService {

    List<Reservation> query(String courtName);

    void make(Reservation reservation)
            throws ReservationNotAvailableException;

    List<SportType> getAllSportTypes();

    SportType getSportType(int sportTypeId);

    List<Reservation> findByDate(LocalDate summaryDate);

    void makePeriodic(PeriodicReservation periodicReservation)
            throws ReservationNotAvailableException;
}
