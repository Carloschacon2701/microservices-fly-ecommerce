package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Hotel.CreateHotelDTO;
import org.carlos.fly_core.Models.Hotel;
import org.carlos.fly_core.Repository.BranchRepository;
import org.carlos.fly_core.Repository.CityRepository;
import org.carlos.fly_core.Repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelService {
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;

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

    public Hotel createHotel(CreateHotelDTO hotel) {
        return hotelRepository.save(Hotel.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .phone(hotel.getPhone())
                .logo(hotel.getLogo())
                .stars(hotel.getStars())
                .city(cityRepository.findById(hotel.getCity_id()).orElseThrow())
                .branch(branchRepository.findById(hotel.getBranch_id()).orElseThrow())
                .build());
    }

}
