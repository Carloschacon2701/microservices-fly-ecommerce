package org.carlos.fly_core.Controllers;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.Models.Room;
import org.carlos.fly_core.Services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    @GetMapping("/hotel/{hotel_id}")
    public ResponseEntity<List<Room>> getRoomByHotel(
            @PathVariable Integer hotel_id
    ) {
        try{
            return ResponseEntity.ok(roomService.getRoomByHotel(hotel_id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(roomService.getRoom(id));
    }


}
