package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Hotel.CreateHotelDTO;
import org.carlos.fly_core.Models.Hotel;
import org.carlos.fly_core.Repository.BranchRepository;
import org.carlos.fly_core.Repository.CityRepository;
import org.carlos.fly_core.Repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelService {
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;
    private final BranchRepository branchRepository;
    private final S3service s3service;

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

    public Hotel createHotel(CreateHotelDTO hotel) throws Exception {
        String fileID = "";


        var hotelExists = hotelRepository.findByName(hotel.getName());

        if (hotelExists.isPresent()) {
            throw new RuntimeException("Hotel already exists");
        }

        if(!hotel.getLogo().isEmpty()){
           fileID =  s3service.uploadFile(hotel.getLogo());
        }

        return hotelRepository.save(Hotel.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .phone(hotel.getPhone())
                .logo(fileID)
                .stars(hotel.getStars())
                .city(cityRepository.findById(hotel.getCity_id()).orElseThrow())
                .branch(branchRepository.findById(hotel.getBranch_id()).orElseThrow())
                .build());
    }


    public Hotel uploadHotelImages(Integer id, List<MultipartFile> images) throws Exception {
        System.out.println(images.size());
        System.out.println(images);
        System.out.println("ARRAY");
        var hotel = hotelRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Hotel not found")
        );

        if(hotel.getImages().size() + images.size() > 5){
            throw new RuntimeException("Max images reached");
        };

        for(MultipartFile image : images){
            String fileID = s3service.uploadFile(image);
            hotel.getImages().add(fileID);
        }

        return hotelRepository.save(hotel);
    }

}
