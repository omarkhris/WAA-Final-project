package com.miu.pmtbackendapi.domain.offer.request;

import com.miu.pmtbackendapi.domain.property.dto.request.PropertyDTO;
import com.miu.pmtbackendapi.domain.property.dto.request.PropertyIdDTO;
import com.miu.pmtbackendapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private String submissionDate;
    private Double offeredPrice;
    private User user;
    private PropertyIdDTO property; //Implementation in progress. Uncomment when implemented.
}
