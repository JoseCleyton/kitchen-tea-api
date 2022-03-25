package com.revelation.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialsDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
