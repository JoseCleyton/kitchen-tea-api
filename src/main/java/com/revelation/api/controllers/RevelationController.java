package com.revelation.api.controllers;

import com.revelation.api.dtos.RevelationDto;
import com.revelation.api.models.RevelationModel;
import com.revelation.api.services.RevelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/revelation")
@CrossOrigin(origins = "*")
public class RevelationController {

    private final RevelationService revelationService;

    public RevelationController(RevelationService revelationService) {
        this.revelationService = revelationService;
    }

    @PostMapping
    public ResponseEntity<RevelationModel> save(@RequestBody @Valid RevelationDto revelationDto) {
        RevelationModel revelationModel = new RevelationModel();
        BeanUtils.copyProperties(revelationDto, revelationModel);
        return ResponseEntity.ok(this.revelationService.save(revelationModel));
    }

    @GetMapping("/key")
    public ResponseEntity<RevelationModel> findByKey(@RequestParam String key) {
        Optional<RevelationModel> optionalRevelationModel = this.revelationService.findByKey(key);
        if (!optionalRevelationModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!this.revelationService.isValid(optionalRevelationModel.get())) {
            return new ResponseEntity(optionalRevelationModel.get(), HttpStatus.LOCKED);
        }
        return ResponseEntity.ok(optionalRevelationModel.get());
    }


    @GetMapping
    public ResponseEntity<List<RevelationModel>> findAll() {
        return ResponseEntity.ok(this.revelationService.findAll());
    }

}
