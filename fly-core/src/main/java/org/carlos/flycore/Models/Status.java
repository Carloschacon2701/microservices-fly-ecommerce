package org.carlos.flycore.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Status {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

}
