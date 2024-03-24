package org.carlos.flycore.DTO.User;

import org.carlos.flycore.Models.Gender;
import org.carlos.flycore.Models.Role;

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
