package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.User.UserDTO;
import org.carlos.fly_core.DTO.User.UserDTOMapper;
import org.carlos.fly_core.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserDTO getUser(Integer id) {
        return userDTOMapper.apply(userRepository.findById(id).orElseThrow());
    }
}
