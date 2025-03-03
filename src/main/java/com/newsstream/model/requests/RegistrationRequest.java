package com.newsstream.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank
    @Size(min = 2, max = 50, message = "is required")
    private String username;

    @NotBlank
    @Size(min = 3, max = 15, message = "Password have to be more than 6 to 15 symbols")
    private String password;

    @NotBlank
    @Size(min = 3, max = 15, message = "Password have to be more than 6 to 15 symbols")
    private String confirmPassword;

    @NotBlank
    @Size(min = 2, max = 50, message = "is required")
    private String email;

    @NotNull(message = "Role id is required")
    private Integer roleId;

    public boolean isValidPassword() {

        return this.password.equals(this.confirmPassword);
    }

}

