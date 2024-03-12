package com.miu.pmtbackendapi.service.enquiry.impl;

import com.miu.pmtbackendapi.domain.enquiry.EnquiryMessage;
import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponse;
import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponses;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.enquiry.EnquiryRepository;
import com.miu.pmtbackendapi.service.commonadpater.Adapter;
import com.miu.pmtbackendapi.service.enquiry.EnquiryService;
import com.miu.pmtbackendapi.service.enquiry.adapter.EnquiryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final Adapter adapter;
    private final EnquiryAdapter enquiryAdapter;

    @Override
    public EnquiryResponse getEnquiryMessage(Long enquiryId) throws ItemNotFoundException {

        Optional<EnquiryMessage> msg = enquiryRepository.findById(enquiryId);
        if (msg.isPresent()) {
            return adapter.convertObject(msg.get(), EnquiryResponse.class);
        }
        throw new ItemNotFoundException("Enquiry message unavailable");
    }

    @Override
    public EnquiryResponses getAllEnquiryResponsesForAProperty(Long propertyId) {
        List<EnquiryMessage> messages = enquiryRepository.getAllEnquiryMessagesForAProperty(propertyId);
        return new EnquiryResponses(enquiryAdapter.getEnquiryResponses(messages));
    }

    @Override
    public EnquiryResponse saveEnquiryMessage(EnquiryResponse response) {
        EnquiryMessage msg = adapter.convertObject(response, EnquiryMessage.class);
        return adapter.convertObject(enquiryRepository.save(msg), EnquiryResponse.class);
    }
}
