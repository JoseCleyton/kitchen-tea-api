package com.revelation.api.dtos;

import com.revelation.api.models.GiftSuggestionModel;
import com.revelation.api.models.RevelationModel;
import lombok.Data;

@Data
public class GiftDto {
    private GiftSuggestionModel giftSuggestion;
    private GuestDto guest;
    private RevelationModel revelation;
}
