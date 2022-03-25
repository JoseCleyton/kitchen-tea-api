package com.revelation.api.repositories;

import com.revelation.api.models.GiftSuggestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftSuggestionRepository extends JpaRepository<GiftSuggestionModel, Long> {
    List<GiftSuggestionModel> findAllByRevelationId(Long id);
}
