package org.carlos.flycore.Repository;

import org.carlos.flycore.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.room.id = :room_id AND r.checkIn <= :checkOut AND r.checkOut >= :checkIn")
    Integer countOverlappingReservations(Integer room_id, Date checkIn, Date checkOut);
}
