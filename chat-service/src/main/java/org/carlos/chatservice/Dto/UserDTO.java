package org.carlos.chatservice.Dto;

import java.util.Date;

public record UserDTO(
        Integer id,
        String name,
        String email,
        Date dateOfBirth

) {
}