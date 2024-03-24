package org.carlos.fly_core.DTO.User;

import org.carlos.fly_core.Models.User;
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
