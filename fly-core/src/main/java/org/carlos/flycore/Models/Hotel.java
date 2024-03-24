package org.carlos.flycore.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Hotel {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    private String name;

    private String address;

    private String phone;

    private String logo;

    private Integer stars;
}
