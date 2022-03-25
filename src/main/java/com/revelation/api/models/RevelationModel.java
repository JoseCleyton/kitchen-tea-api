package com.revelation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REVELATION")
public class RevelationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String key;
    private int quantityGifts;
    private int quantityGuest;
    private LocalDateTime expiredIn;
    @JsonIgnore
    @OneToMany(mappedBy = "revelation")
    private List<GiftSuggestionModel> giftSuggestion;
    @JsonIgnore
    @OneToMany(mappedBy = "revelation")
    private List<GiftModel> gift;
}
