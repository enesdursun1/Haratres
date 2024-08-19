package com.haratres.SpringSecurity.business.dtos.user;

import com.haratres.SpringSecurity.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserResponse {

    private int userId;

    private String username;

    private String password;


}
