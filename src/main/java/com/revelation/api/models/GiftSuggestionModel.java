package com.revelation.api.models;

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
@Table(name = "TB_GIFT_SUGGESTION")
public class GiftSuggestionModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;
    private String picture;
    @JsonIgnore
    @OneToOne(mappedBy = "giftSuggestion")
    private GiftModel giftModel;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "revelationId", referencedColumnName = "id")
    private RevelationModel revelation;
}
