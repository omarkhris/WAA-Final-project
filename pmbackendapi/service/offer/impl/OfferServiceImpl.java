package com.miu.pmtbackendapi.service.offer.impl;

import com.itextpdf.text.DocumentException;
import com.miu.pmtbackendapi.domain.auth.UserRole;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.offer.Offer;
import com.miu.pmtbackendapi.domain.offer.request.OfferDTO;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.property.dto.request.PropertyDTO;
import com.miu.pmtbackendapi.domain.user.User;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.property.PropertyRepository;
import com.miu.pmtbackendapi.repo.offer.OfferRepository;
import com.miu.pmtbackendapi.repo.user.UserRepository;
import com.miu.pmtbackendapi.service.commonadpater.Adapter;
import com.miu.pmtbackendapi.service.offer.OfferService;
import com.miu.pmtbackendapi.service.property.impl.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PropertyServiceImpl propertyService;

    @Autowired
    Adapter adapter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public OfferDTO placeOffer(OfferDTO offerDTO) throws ItemNotFoundException {

        User existingUser = userRepository.findById(offerDTO.getUser().getUserId()).get();

        Property existingProperty = propertyRepository.getPropertyByPropertyId(offerDTO.getProperty().getPropertyId());

        Offer offer = adapter.convertObject(offerDTO, Offer.class);

        Property property = adapter.convertObject(offerDTO.getProperty(), Property.class);

        offer.setProperty(property);
        offer.setOfferedPrice(offerDTO.getOfferedPrice());
        offer.setOfferedPrice(offerDTO.getOfferedPrice());
        offer.setSubmissionDate(offerDTO.getSubmissionDate());

        for (UserRole userRole: existingUser.getUserRole()) {
            if (userRole.getUser_role().equals("Customer")) {
                offerRepository.save(offer);
            } else
                throw new ItemNotFoundException("Cannot Place an Offer");
        }

        return adapter.convertObject(offer,OfferDTO.class);
    }

    @Override
    public void deleteOfferById(int offerId) throws ItemNotFoundException {
        Offer offer = offerRepository.getOfferByOfferId(offerId);
        if ((offer.getProperty().getStatusEnum().toString()).equalsIgnoreCase("Pending")) {
            offerRepository.removeByOfferId(offerId);
        } else
            throw new ItemNotFoundException("Cannot delete offer");
    }

    @Override
    public String getReceiptOfOffer(int offerId, int customerId) throws ItemNotFoundException, DocumentException, IOException {

        Offer offer = offerRepository.getOfferByOfferId(offerId);
        if ((offer.getProperty().getStatusEnum().toString()).equalsIgnoreCase("Contingent")) {

            return offer.getOfferedPrice() + "is offerred for" + offer.getProperty();
            //  generateReceipt();


//            byte[] pdfBytes = PdfGenerator.generatePdf(Arrays.asList(offer.getOfferId().toString(),"is generated with price ", offer.getOfferedPrice().toString() ,"for customer Id: ",Integer.toString(customerId)));
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData("attachment", "example.pdf");
//            headers.setContentLength(pdfBytes.length);
//            return new ResponseEntity<String>(pdfBytes, headers, HttpStatus.OK);

        } else
            throw new ItemNotFoundException("Cannot delete offer");
    }

    @Override
    public List<Offer> getAllOffersByUserId(int userId) {
        return offerRepository.findAllByOfferByUserId(userId);
    }

    @Override
    public List<Offer> getOfferHistoryByPropertyId(long propertyId) {
        return offerRepository.getOfferHistoryByPropertyId(propertyId);
    }

    @Override
    public Offer getOfferByOfferId(Long offerId) {
        return offerRepository.getOfferByOfferId(offerId);
    }

    @Override
    public void changeStatusProperty(int offerId) {

      Offer offer = offerRepository.findById(offerId).get();

      Property property = offer.getProperty();
        System.out.println("property.getStatusEnum().getValue() = " + property.getStatusEnum().getValue());
      if (property.getStatusEnum().getValue().equals("AVAILABLE")){
            propertyService.saveStatusProperty(property);
      }else if (property.getStatusEnum().getValue().equals("AVAILABLE")){
          propertyService.cancelStatusProperty(property);
      }
    }


    @Override
    public boolean cancelOfferChangeStatusProperty(int offerId) {

        Offer offer = offerRepository.findById(offerId).get();
        Property property = offer.getProperty();
        System.out.println("property.getStatusEnum().getValue() = " + property.getStatusEnum().getValue());
        if (property.getStatusEnum().getValue().equals("PENDING") ||property.getStatusEnum().getValue().equals("AVAILABLE")){
            propertyService.cancelStatusProperty(property);
            return false;
        }else if (property.getStatusEnum().getValue().equals(PropertyStatusEnum.CONTINGENT)){

        }
        return true;

    }
}
