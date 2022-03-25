package com.revelation.api.controllers;

import com.revelation.api.dtos.GiftSuggestionDto;
import com.revelation.api.models.GiftSuggestionModel;
import com.revelation.api.repositories.GiftSuggestionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gift-suggestion")
@CrossOrigin(origins = "*")
public class GiftSuggestionController {
    private final GiftSuggestionRepository giftSuggestionRepository;

    public GiftSuggestionController(GiftSuggestionRepository giftSuggestionRepository) {
        this.giftSuggestionRepository = giftSuggestionRepository;
    }


    @PostMapping
    public ResponseEntity<GiftSuggestionModel> save(@RequestBody @Valid GiftSuggestionDto giftSuggestionDto) {
        var giftSuggestionModel = new GiftSuggestionModel();
        BeanUtils.copyProperties(giftSuggestionDto, giftSuggestionModel);
        return ResponseEntity.ok(this.giftSuggestionRepository.save(giftSuggestionModel));
    }

    @GetMapping
    public ResponseEntity<List<GiftSuggestionModel>> findAll() {
        return ResponseEntity.ok(this.giftSuggestionRepository.findAll());
    }

    @GetMapping("/revelation")
    public ResponseEntity<List<GiftSuggestionModel>> findAllByRevelationId(@RequestParam Long revelationId) {
        if (revelationId == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.giftSuggestionRepository.findAllByRevelationId(revelationId));
    }

}
