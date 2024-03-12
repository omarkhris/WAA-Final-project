package com.miu.pmtbackendapi.domain.enquiry;

import com.miu.pmtbackendapi.domain.property.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnquiryMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enqId;
    private String fullName;
    private String email;
    private String contact;
    private String message;
    private LocalDateTime messageTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    Property property;
}
