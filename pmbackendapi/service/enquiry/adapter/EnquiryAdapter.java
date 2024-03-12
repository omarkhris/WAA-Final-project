package com.miu.pmtbackendapi.service.enquiry.adapter;

import com.miu.pmtbackendapi.domain.enquiry.EnquiryMessage;
import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponse;
import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponses;
import com.miu.pmtbackendapi.service.commonadpater.Adapter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnquiryAdapter {

    private final ModelMapper modelMapper;

    private final Adapter adapter;

    public List<EnquiryResponse> getEnquiryResponses(List<EnquiryMessage> messages) {
        return messages.stream()
                .map(em -> adapter.convertObject(em, EnquiryResponse.class))
                .collect(Collectors.toList());
    }
}
