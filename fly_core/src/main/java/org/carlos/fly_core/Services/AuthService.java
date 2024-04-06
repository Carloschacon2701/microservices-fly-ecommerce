package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Auth.UserRegister;
import org.carlos.fly_core.DTO.User.UserDTO;
import org.carlos.fly_core.DTO.User.UserDTOMapper;
import org.carlos.fly_core.Models.Gender;
import org.carlos.fly_core.Models.Role;
import org.carlos.fly_core.Models.User;
import org.carlos.fly_core.Repository.GenderRepository;
import org.carlos.fly_core.Repository.RoleRepository;
import org.carlos.fly_core.Repository.UserRepository;
import org.carlos.fly_core.Security.Services.CognitoService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CognitoService cognitoService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;
    private final UserDTOMapper userDTOMapper;

    public UserDTO signUp(UserRegister user){
        var existUser = userRepository.findByEmail(user.getEmail());

        if(existUser.isPresent()){
            throw new RuntimeException("User already exists");
        }

        cognitoService.CognitoSignUp(user.getEmail(), user.getPassword());

        Gender gender = genderRepository.findById(user.getGenderId())
                .orElseThrow(() -> new RuntimeException("Invalid Gender")
        );

        Role role = roleRepository.findById(user.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role")
        );

        User newUser = User.builder()
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .gender(gender)
                .role(role)
                .build();

       return userDTOMapper.apply(userRepository.save(newUser));
    }

    public AuthenticationResponse initiateAuth(AuthRequest request) {
        AuthenticationResultType response = cognitoService.CognitoSignIn(request.getEmail(), request.getPassword());

        return AuthenticationResponse.builder()
                .refreshToken(response.refreshToken())
                .accessToken(response.accessToken())
                .build();

    }
}
