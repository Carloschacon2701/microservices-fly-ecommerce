package org.carlos.fly_core.DTO.User;

import org.carlos.fly_core.Models.Gender;
import org.carlos.fly_core.Models.Role;

import java.util.Date;

public record UserDTO(
        Integer id,
        String name,
        String email,
        Date dateOfBirth,
        Role role,

        Gender gender

) {
}
