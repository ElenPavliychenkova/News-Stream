package com.newsstream.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotBlank
    @Size(min = 2, max = 50, message = "is required")
    private String username;

    @NotBlank
    @Size(min = 3, max = 15, message = "Password have to be more than 6 to 15 symbols")
    private String password;

}
