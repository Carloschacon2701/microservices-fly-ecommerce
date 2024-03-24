package org.carlos.flycore.DTO.User;

import org.carlos.flycore.Models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getGender()
        );
    }
}
