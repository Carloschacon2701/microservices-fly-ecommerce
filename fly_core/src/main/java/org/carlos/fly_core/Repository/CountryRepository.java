package org.carlos.fly_core.Repository;

import org.carlos.fly_core.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
