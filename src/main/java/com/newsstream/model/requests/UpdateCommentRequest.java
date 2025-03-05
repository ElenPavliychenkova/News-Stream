package com.newsstream.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCommentRequest {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 500)
    private String text;

}
