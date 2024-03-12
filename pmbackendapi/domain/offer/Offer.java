package com.miu.pmtbackendapi.domain.offer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    private String submissionDate;

    @JsonBackReference
    @ManyToOne
    User user;

    private Double offeredPrice;

    @OneToOne
    Property property;


}
