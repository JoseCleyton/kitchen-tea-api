package com.revelation.api.repositories;

import com.revelation.api.models.GuestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GuestRepository extends JpaRepository<GuestModel, Long> {

    @Query(value = "SELECT COUNT (id) FROM tb_gift WHERE revelation_id = :revelattionId", nativeQuery = true)
    int findQuantityGiftByRevelationId(Long revelattionId);
}
