package com.miu.pmtbackendapi.domain.offer.response;

import com.miu.pmtbackendapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {
    private LocalDateTime submissionDate;
    private Double offeredPrice;
    private User user;
    //private Property property; //Implementation in progress. Uncomment after implementation completed.
}
