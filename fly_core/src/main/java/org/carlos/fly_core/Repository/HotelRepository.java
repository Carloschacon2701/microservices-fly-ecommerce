package org.carlos.fly_core.Repository;

import org.carlos.fly_core.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Optional<List<Hotel>> findAllByCity_Id(Integer city_id);

    @Query("SELECT h FROM Hotel h WHERE h.city.country.id = :country_id")
    Optional<List<Hotel>> findByCountry(Integer country_id);


}
