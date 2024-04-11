package org.carlos.fly_core.Repository.Reservation;

import org.carlos.fly_core.Models.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Integer> {
}
