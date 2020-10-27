package com.study.mvc.service;

import java.time.LocalDate;
import java.util.List;

import com.study.mvc.domain.PeriodicReservation;
import com.study.mvc.domain.Reservation;
import com.study.mvc.domain.SportType;

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
