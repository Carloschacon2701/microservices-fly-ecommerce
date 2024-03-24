package org.carlos.flycore.Repository;

import org.carlos.flycore.Models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
