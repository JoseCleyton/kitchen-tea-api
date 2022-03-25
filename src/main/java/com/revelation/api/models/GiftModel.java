package com.revelation.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_GIFT")
public class GiftModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "giftSuggestionId", referencedColumnName = "id")
    private GiftSuggestionModel giftSuggestion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guestId", referencedColumnName = "id")
    private GuestModel guest;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "revelationId", referencedColumnName = "id")
    private RevelationModel revelation;

}
