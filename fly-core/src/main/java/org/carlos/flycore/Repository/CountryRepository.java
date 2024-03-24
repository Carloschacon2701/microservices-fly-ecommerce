package org.carlos.flycore.Repository;

import org.carlos.flycore.Models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
