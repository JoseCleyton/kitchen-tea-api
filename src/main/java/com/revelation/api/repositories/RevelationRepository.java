package com.revelation.api.repositories;

import com.revelation.api.models.RevelationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevelationRepository extends JpaRepository<RevelationModel, Long> {
    Optional<RevelationModel> findByKey(String key);
}
