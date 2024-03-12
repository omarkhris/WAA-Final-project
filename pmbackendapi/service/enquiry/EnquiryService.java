package com.miu.pmtbackendapi.service.enquiry;

import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponse;
import com.miu.pmtbackendapi.domain.enquiry.dto.EnquiryResponses;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;

import java.util.Optional;

public interface EnquiryService {

    EnquiryResponse getEnquiryMessage(Long enquiryId) throws ItemNotFoundException;

    EnquiryResponses getAllEnquiryResponsesForAProperty(Long propertyId);

    EnquiryResponse saveEnquiryMessage(EnquiryResponse response);
}
