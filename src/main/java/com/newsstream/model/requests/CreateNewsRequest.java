package com.newsstream.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNewsRequest {

    @NotBlank
    @Size(min = 5, max = 150)
    private String title;

    @NotBlank
    @Size(min = 5, max = 350)
    private String brief;

    @NotBlank
    @Size(min = 5, max = 500)
    private String text;

}
