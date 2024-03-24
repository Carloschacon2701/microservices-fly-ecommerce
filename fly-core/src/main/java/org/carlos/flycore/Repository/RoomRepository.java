package org.carlos.flycore.Repository;

import org.carlos.flycore.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<List<Room>> findAllByHotel_Id(Integer hotel_id);
}
