package org.carlos.fly_core.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gender")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Gender {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
