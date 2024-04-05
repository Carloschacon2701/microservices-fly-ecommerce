package org.carlos.fly_core.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Hotel.CreateHotelDTO;
import org.carlos.fly_core.Models.Hotel;
import org.carlos.fly_core.Services.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;


    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @GetMapping("/city/{city_id}")
    public ResponseEntity<List<Hotel>> getHotelsByCity(
            @PathVariable Integer city_id
    ) {
        try {
            return ResponseEntity.ok(hotelService.getHotelsByCity(city_id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/country/{country_id}")
    public ResponseEntity<List<Hotel>> getHotelsByCountry(
            @PathVariable Integer country_id
    ) {
        try{
            return ResponseEntity.ok(hotelService.getHotelsByCountry(country_id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(
            @RequestBody @Valid CreateHotelDTO hotel
    ) {
        return ResponseEntity.ok(hotelService.createHotel(hotel));
    }



}
