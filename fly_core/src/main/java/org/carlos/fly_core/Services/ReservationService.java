package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Reservation.ReservationCreationDTO;
import org.carlos.fly_core.Models.*;
import org.carlos.fly_core.PaymentRail.DTO.StripeChargeDTO;
import org.carlos.fly_core.PaymentRail.Services.StripeService;
import org.carlos.fly_core.Repository.Reservation.ReservationRepository;
import org.carlos.fly_core.Repository.Reservation.ReservationStatusRepository;
import org.carlos.fly_core.Repository.RoomRepository;
import org.carlos.fly_core.Repository.StatusRepository;
import org.carlos.fly_core.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final StripeService stripeService;
    private final ReservationStatusRepository reservationStatusRepository;
    private final StatusRepository statusRepository;

    public Reservation getReservation(Integer id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public Reservation createReservation(ReservationCreationDTO reservation) {

        User user = userRepository.findById(reservation.getUser_id()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        Room room = roomRepository.findById(reservation.getRoom_id()).orElseThrow(
            () -> new RuntimeException("Room not found")
        );

        Integer overLappingReservations = reservationRepository.countOverlappingReservations(
            reservation.getRoom_id(), reservation.getCheckIn(), reservation.getCheckOut()
        );

        System.out.println(overLappingReservations);

        if(overLappingReservations > room.getQuantity()){
            throw new RuntimeException("Room is not available for the selected dates");
        }

        if(reservation.getAdults() + reservation.getChildren() > room.getCapacity()){
            throw new RuntimeException("Room capacity exceeded");
        }

        Double total = room.getPrice() * reservation.getDays();


        Reservation newReservation =  reservationRepository.save(
                Reservation.builder()
                        .days(reservation.getDays())
                        .checkIn(reservation.getCheckIn())
                        .checkOut(reservation.getCheckOut())
                        .adults(reservation.getAdults())
                        .children(reservation.getChildren())
                        .totalPrice(total)
                        .user(user)
                        .room(room)
                        .build()
        );

         reservationStatusRepository.save(
                ReservationStatus.builder()
                        .reservation(newReservation)
                        .status(statusRepository.findById(1).orElseThrow(
                                () -> new RuntimeException("Status not found")
                        ))
                        .created(new Date())
                        .build()
        );

        return newReservation;
    }

    public Reservation payReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reservation not found")
        );

        try{
            StripeChargeDTO chargeDTO = StripeChargeDTO.builder()
                    .amount(reservation.getTotalPrice())
                    .message("Payment for reservation " + reservation.getId())
                    .username(reservation.getUser().getUsername())
                    .build();

            boolean result = stripeService.charge(chargeDTO);

            if(!result){
                throw new RuntimeException("Payment failed");
            }

            reservation.setPaid(true);

            reservationStatusRepository.save(
                    ReservationStatus.builder()
                            .reservation(reservation)
                            .status(statusRepository.findById(3).orElseThrow(
                                    () -> new RuntimeException("Status not found")
                            ))
                            .created(new Date())
                            .build()
            );

            return reservationRepository.save(reservation);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Reservation deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reservation not found")
        );

        List<ReservationStatus> reservationStatus = reservationStatusRepository
                .findAllByReservationOrderByCreatedAsc(reservation)
                .orElse(null);



        Status cancelledStatus = statusRepository.findById(2).orElseThrow(
                () -> new RuntimeException("Status not found")
        );

        if(reservationStatus.get(0) == null) {
            throw new RuntimeException("Reservation status not found");
        }

        if(reservationStatus.get(0).getStatus().getId() == 2){
            throw new RuntimeException("Reservation already cancelled");
        }

        if(reservationStatus.get(0).getStatus().getId() == 3){
            throw new RuntimeException("Reservation already paid");
        }

        reservationStatusRepository.save(
                ReservationStatus.builder()
                        .reservation(reservation)
                        .status(cancelledStatus)
                        .build()
        );

        return reservation;
    }

}
