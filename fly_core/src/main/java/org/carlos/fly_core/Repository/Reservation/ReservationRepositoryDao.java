package org.carlos.fly_core.Repository.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.Models.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryDao {
    private final EntityManager entityManager;

    public Integer countOverLappingReservations(Integer room_id, Date checkIn, Date checkOut) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> query = cb.createQuery(Reservation.class);

        Root<Reservation> root = query.from(Reservation.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get("room").get("id"), room_id));

        predicates.add(cb.lessThanOrEqualTo(root.get("checkIn"), checkOut));

        predicates.add(cb.greaterThanOrEqualTo(root.get("checkOut"), checkIn));

        Predicate andPredicate = cb.and(predicates.toArray(new Predicate[0]));

        query.where(andPredicate);


        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Reservation.class)));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return count.intValue();


    }


}
