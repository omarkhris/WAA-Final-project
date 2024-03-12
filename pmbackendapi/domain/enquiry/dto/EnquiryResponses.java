package com.miu.pmtbackendapi.domain.enquiry.dto;

import com.miu.pmtbackendapi.domain.property.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnquiryResponses {
    List<EnquiryResponse> enquiryRes;
}
