package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.Models.Hotel;
import org.carlos.fly_core.Repository.CityRepository;
import org.carlos.fly_core.Repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelService {
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;

    public Hotel getHotel(Integer id) {
        return hotelRepository.findById(id).orElseThrow();
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> getHotelsByCity(Integer city_id) {
        return hotelRepository.findAllByCity_Id(city_id).orElseThrow();
    }

    public List<Hotel> getHotelsByCountry(Integer country_id) {
        return hotelRepository.findByCountry(country_id).orElseThrow();
    }

}
