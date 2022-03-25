package com.revelation.api.dtos;

import com.revelation.api.models.RevelationModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class GiftSuggestionDto {

    @NotBlank
    private String name;
    @NotBlank
    private String url;
    private String picture;
    private RevelationModel revelation;
}
