package com.miu.pmtbackendapi.repo.enquiry;

import com.miu.pmtbackendapi.domain.enquiry.EnquiryMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryMessage, Long> {

    @Query("Select em from EnquiryMessage  em JOIN Property p where p.propertyId=:propertyId")
    List<EnquiryMessage> getAllEnquiryMessagesForAProperty(@Param(value = "propertyId") Long propertyId);
}
