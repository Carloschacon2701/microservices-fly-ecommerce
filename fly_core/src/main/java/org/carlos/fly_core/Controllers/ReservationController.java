package org.carlos.fly_core.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Reservation.ReservationCreationDTO;
import org.carlos.fly_core.Models.Reservation;
import org.carlos.fly_core.Services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.getReservation(id));

    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationCreationDTO reservation) {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable  Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }


}
