package org.carlos.fly_core.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "branch")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Branch {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String description;

    private byte[] logo;
}
