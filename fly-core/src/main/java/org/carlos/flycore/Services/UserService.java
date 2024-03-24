package org.carlos.flycore.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.flycore.DTO.User.UserDTO;
import org.carlos.flycore.DTO.User.UserDTOMapper;
import org.carlos.flycore.Repository.UserRepository;
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
