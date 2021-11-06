package com.bike.service;

import com.bike.model.Reservation;
import com.bike.model.custom.CountClient;
import com.bike.model.custom.StatusAmount;
import com.bike.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepository.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (e.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public boolean deleteReservation(int id) {

        Optional<Reservation> reservation = getReservation(id);

        if (reservation.isEmpty()) {
            return false;
        } else {
            reservationRepository.delete(reservation.get());
            return true;
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation()!= null) {
            Optional<Reservation> reserva = reservationRepository.getReservation(reservation.getIdReservation());
            if (!reserva.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reserva.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reserva.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!= null) {
                    reserva.get().setStatus(reservation.getStatus());
                }
                
                return reservationRepository.save(reserva.get());
            }
        }
        return reservation;
    }
    
    public List<Reservation> getReservationsPeriod(String dateA, String dateB){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date a= new Date();
        Date b=new Date();
        try {
            a = parser.parse(dateA);
            b = parser.parse(dateB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationPeriod(a,b);
        }else{
            return new ArrayList<>();
        }

    }
    
    public StatusAmount getReservationsStatusReport(){
        List<Reservation>completed=reservationRepository.getReservationsByStatus("completed");
        List<Reservation>cancelled=reservationRepository.getReservationsByStatus("cancelled");
        return new StatusAmount(completed.size(),cancelled.size());

    }
    
    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClients();
    }
    
    
}
