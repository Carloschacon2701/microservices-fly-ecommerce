package org.carlos.fly_core.DTO.Auth;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegister {
    private String email;
    private String password;
    private Date dateOfBirth;
    private Integer genderId;
    private Integer roleId;
}
