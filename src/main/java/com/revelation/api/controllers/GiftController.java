package com.revelation.api.controllers;

import com.revelation.api.dtos.GiftDto;
import com.revelation.api.models.GiftModel;
import com.revelation.api.models.GiftSuggestionModel;
import com.revelation.api.models.GuestModel;
import com.revelation.api.services.GiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gift")
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @PostMapping
    public ResponseEntity<GiftModel> save(@RequestBody @Valid GiftDto giftDto) {
        var giftModel = new GiftModel();
        var guestModel = new GuestModel();
        var giftSuggestionModel = new GiftSuggestionModel();

        BeanUtils.copyProperties(giftDto, giftModel);
        BeanUtils.copyProperties(giftDto.getGuest(), guestModel);
        BeanUtils.copyProperties(giftDto.getGiftSuggestion(), giftSuggestionModel);

        giftModel.setGuest(guestModel);
        giftModel.setGiftSuggestion(giftSuggestionModel);
        return ResponseEntity.ok(this.giftService.save(giftModel));
    }

    @GetMapping
    public ResponseEntity<List<GiftModel>> findByAll() {
        return ResponseEntity.ok(this.giftService.findAll());
    }

    @GetMapping("/guest")
    public ResponseEntity<GiftModel> findByGuestName(@RequestParam String guestName) {
        var optionalGuest = this.giftService.findByGuestName(guestName);
        if (optionalGuest.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(optionalGuest.get());
    }


}
