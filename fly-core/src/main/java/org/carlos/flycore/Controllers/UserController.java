package org.carlos.flycore.Controllers;

import lombok.RequiredArgsConstructor;
import org.carlos.flycore.DTO.User.UserDTO;
import org.carlos.flycore.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
