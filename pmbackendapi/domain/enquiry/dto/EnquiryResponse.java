package com.miu.pmtbackendapi.domain.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miu.pmtbackendapi.domain.property.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnquiryResponse {
    private String fullName;
    private String email;
    private String contact;
    private String message;

    private LocalDateTime messageTime;


    //Use this propertyDTO when propertyDTO is finalized
    // TODO: 25/04/2023 this is remaining to do
    Property property;
}
