package com.cuervo.Preparcial.Controller;

import com.cuervo.Preparcial.model.Role;
import lombok.Data;

@Data
class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
