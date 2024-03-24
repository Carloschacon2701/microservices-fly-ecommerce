package org.carlos.flycore.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "reservation_status")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationStatus {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private Date created_at = new Date();
}
