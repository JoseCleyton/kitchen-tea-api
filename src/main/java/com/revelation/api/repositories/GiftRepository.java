package com.revelation.api.repositories;

import com.revelation.api.models.GiftModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GiftRepository extends JpaRepository<GiftModel, Long> {

    Optional<GiftModel> findByGuestName(String guestName);

    @Query(value = "SELECT COUNT (id) FROM tb_gift WHERE revelation_id = :revelattionId", nativeQuery = true)
    int findQuantityGiftByRevelationId(Long revelattionId);


    @Query(value = "SELECT COUNT (A.id) FROM tb_gift A LEFT JOIN tb_guest B ON A.guest_id = B.id WHERE revelation_id = :revelattionId", nativeQuery = true)
    int findQuantityGuestByRevelationId(Long revelattionId);
}
