package org.carlos.fly_core.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.carlos.fly_core.DTO.Auth.AuthRequest;
import org.carlos.fly_core.DTO.Auth.UserRegister;
import org.carlos.fly_core.Security.Services.CognitoService;
import org.carlos.fly_core.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/security")
public class SecurityController {
    private final AuthService authenticationService;

    @PostMapping(path = "/register/cognito")
    public ResponseEntity<?> registerCognito(@RequestBody UserRegister request){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping(path="/login/cognito")
    public ResponseEntity<?> loginCognito(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(authenticationService.logIn(request));
    }
}
