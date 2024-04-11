package org.carlos.fly_core.DTO.Auth;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UserRegister {
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @NotNull(message= "Gender is required")
    private Integer genderId;

    @NotNull(message = "Role is required")
    private Integer roleId;
}
