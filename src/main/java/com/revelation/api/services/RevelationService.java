package com.revelation.api.services;

import com.revelation.api.models.RevelationModel;
import com.revelation.api.repositories.RevelationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RevelationService {

    private final RevelationRepository revelationRepository;
    private final GiftService giftService;

    public RevelationService(RevelationRepository configRepository, GiftService giftService) {
        this.revelationRepository = configRepository;
        this.giftService = giftService;
    }

    public RevelationModel save(RevelationModel revelationModel) {
        return this.revelationRepository.save(revelationModel);
    }

    public Optional<RevelationModel> findByKey(String key) {
        return this.revelationRepository.findByKey(key);

    }

    public List<RevelationModel> findAll() {
        List<RevelationModel> list = this.revelationRepository.findAll();
        list.stream()
                .forEach(el -> {
                            el.setQuantityGifts(this.giftService.quantityGiftByRevelation(el.getId()));
                            el.setQuantityGuest(this.giftService.quantityGuestByRevelation(el.getId()));
                        }

                );

        return list;
    }

    public boolean isValid(RevelationModel revelationModel) {
        if (revelationModel.getExpiredIn().isAfter(LocalDateTime.now())
                || revelationModel.getExpiredIn().isEqual(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        this.revelationRepository.deleteById(id);
    }

    public RevelationModel update(RevelationModel revelationModel) {
        return this.revelationRepository.save(revelationModel);
    }
}
