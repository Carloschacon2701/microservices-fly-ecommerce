package org.carlos.fly_core.Repository.Reservation;

import org.carlos.fly_core.Models.Reservation;
import org.carlos.fly_core.Models.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Integer> {

    Optional<List<ReservationStatus>> findAllByReservationOrderByCreatedAsc(Reservation reservation_id);

}
