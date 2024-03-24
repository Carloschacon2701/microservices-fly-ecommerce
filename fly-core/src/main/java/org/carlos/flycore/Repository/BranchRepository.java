package org.carlos.flycore.Repository;

import org.carlos.flycore.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query("SELECT b FROM Hotel h JOIN Branch b ON h.branch.id = b.id WHERE h.id = :hotel_id")
    Optional<Branch> findBranchByHotel (Integer hotel_id);

    Optional<Branch> findByEmail(String email);

    Optional<Branch> findByName(String name);
}
