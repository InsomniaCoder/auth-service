package com.insomniacoder.authservice.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private String name;

    @Min(value = 4, message = "Password must be at least 4")
    @Max(value = 10, message = "Password must not exceed 10")
    private String password;

    @NotNull
    private String role;
}
