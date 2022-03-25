package com.revelation.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RevelationDto {
    @NotBlank
    private String key;
    @NotBlank
    private String name;
    @NotNull
    private LocalDateTime expiredIn;
}
