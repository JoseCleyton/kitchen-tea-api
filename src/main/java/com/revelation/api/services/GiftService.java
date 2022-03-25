package com.revelation.api.services;

import com.revelation.api.models.GiftModel;
import com.revelation.api.repositories.GiftRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GiftService {

    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }


    @Transactional
    public GiftModel save(GiftModel giftModel) {
//        RestTemplate restTemplate = new RestTemplate();
//        var guestDto = new GuestDto();
//        BeanUtils.copyProperties(giftModel.getGuest(), guestDto);
//        HttpEntity<GiftModel> request = new HttpEntity(guestDto);
//        var guestSaved =
//                restTemplate
//                        .postForObject("http://localhost:8081/guest", request, GuestModel.class);
//        giftModel.setGuest(guestSaved);
        return this.giftRepository.save(giftModel);
    }

    public List<GiftModel> findAll() {
        return this.giftRepository.findAll();
    }

    public Optional<GiftModel> findByGuestName(String guestName) {
        return this.giftRepository.findByGuestName(guestName);
    }

    public int quantityGiftByRevelation(Long revelationId) {
        return this.giftRepository.findQuantityGiftByRevelationId(revelationId);
    }
    public int quantityGuestByRevelation(Long revelationId) {
        return this.giftRepository.findQuantityGuestByRevelationId(revelationId);
    }

}
