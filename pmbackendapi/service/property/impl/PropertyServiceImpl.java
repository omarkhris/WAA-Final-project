package com.miu.pmtbackendapi.service.property.impl;


import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import com.miu.pmtbackendapi.domain.image.PropertyImage;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.property.dto.response.ResponseProperties;
import com.miu.pmtbackendapi.domain.property.dto.response.ResponseProperty;
import com.miu.pmtbackendapi.exception.CustomMessage;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.repo.address.AddressRepo;
import com.miu.pmtbackendapi.repo.offer.OfferRepository;
import com.miu.pmtbackendapi.repo.property.PropertyRepository;
import com.miu.pmtbackendapi.repo.propertydetail.PropertyDetailRepository;
import com.miu.pmtbackendapi.service.commonadpater.Adapter;
import com.miu.pmtbackendapi.service.property.PropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private final PropertyRepository propertyRepo;
    PropertyStatusEnum propertyStatusAvaiable;
    @Autowired
    private OfferRepository offerRepository;
    private final Adapter adapter;
    private final AddressRepo addressRepo;
    private final PropertyDetailRepository propertyDetailRepo;

    private PropertyStatusEnum propertyStatusEnum;

    @Override
    public List<ResponseProperty> getAllProperties() {

        List<Property> propertyList = propertyRepo.findAll();
        List<ResponseProperty> propertyDtos = propertyList.stream()
                .map(p -> adapter.convertObject(p, ResponseProperty.class))
                .collect(Collectors.toList());
        return propertyDtos;
    }

    @Override
    public ResponseProperty getPropertyById(Long id) throws ItemNotFoundException {
        Optional<Property> find = propertyRepo.findById(id);
        if (find.isPresent()) {
            Property property = find.get();
            ResponseProperty propertyDto = adapter.convertObject(property, ResponseProperty.class);
            return propertyDto;
        } else
            throw new ItemNotFoundException("Property Not Found !!!");
    }


    @Override
    public ResponseProperty createProperty(Property property) {

//        List<PropertyImage> images = Arrays.stream(files)
//                .filter(f -> f.isEmpty())
//                .map(m -> {
//                    PropertyImage single = new PropertyImage();
//
//                    try {
//                        String fileName = m.getOriginalFilename();
//                        String main = m.getName();
//                        long fileSize = m.getSize();
//                        byte[] data = m.getBytes();
//                        single.setImageByte(data);
////                        single.setImageLocation();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return single;
//
//                }).collect(Collectors.toList());
//
//        property.setPropertyImages(images);
        Property newProperty = propertyRepo.save(property);
        ResponseProperty propertyDto = adapter.convertObject(newProperty, ResponseProperty.class);
        return propertyDto;
    }

    @Override
    public void saveStatusProperty(Property property) {

        PropertyStatusEnum propertyStatusEnum1 = PropertyStatusEnum.CONTINGENT;
        propertyStatusEnum1.setValue("CONTINGENT");
        property.setStatusEnum(propertyStatusEnum1);
        propertyRepo.save(property);

    }

    @Override
    public void cancelStatusProperty(Property property) {

        PropertyStatusEnum propertyStatusEnum1 = PropertyStatusEnum.AVAILABLE;
        propertyStatusEnum1.setValue("AVAILABLE");
        property.setStatusEnum(propertyStatusEnum1);
        propertyRepo.save(property);

    }

    @Override
    public List<ResponseProperty> getPropertyByOwner(Long owner) throws ItemNotFoundException {
        Optional<List<Property>> find = propertyRepo.findPropertyByOwner_UserId(owner);
        if (find.isPresent() && !find.get().isEmpty()) {
            List<Property> found = find.get();
            List<ResponseProperty> propRes = found.stream().map(p -> adapter.convertObject(p, ResponseProperty.class))
                    .collect(Collectors.toList());
            return propRes;
        } else
            throw new ItemNotFoundException("No Property Registered by This Owner");
    }

    @Override
    public CustomMessage deletePropertyById(Long id) {
        Optional<Property> find = propertyRepo.findById(id);
        if (find.isPresent()) {
            Property prop = find.get();
            if (prop.getStatusEnum().getValue().equals(PropertyStatusEnum.PENDING.getValue())) {
                return new CustomMessage("Failed to deleted. Property is PENDING.", HttpStatus.BAD_REQUEST);
            } else {
                propertyRepo.delete(prop);
                return new CustomMessage("Property successfully deleted.", HttpStatus.OK);
            }
        } else
            return new CustomMessage("Property successfully failed.", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseProperty updateProperty(Long id, Property property) {
        Optional<Property> find = propertyRepo.findById(id);
        if (find.isPresent()) {
            Property found = find.get();
            found.setPropertyDetail(property.getPropertyDetail());
            found.setPropertyImages(property.getPropertyImages());
            found.setOwner(property.getOwner());
            found.setStatusEnum(property.getStatusEnum());
            found.setAddress(property.getAddress());
            Property prop = propertyRepo.save(found);

            ResponseProperty propertyDto = adapter.convertObject(prop, ResponseProperty.class);
            return propertyDto;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
    }

    @Override
    public Boolean cancelProperty(Long id) {
        return null;
    }

    @Override
    public ResponseProperties getPropertiesByParam(String street, String city, String state, String zip,
                                                   String country, PropertyTypeEnum propertyType, Integer roomNum,
                                                   Double propertyPrice) {

        List<Property> properties = propertyRepo.findPropertiesByParam(street, city, state, zip, country, propertyType, roomNum, propertyPrice);
        List<ResponseProperty> propertyDtos = properties.stream()
                .map(p -> adapter.convertObject(p, ResponseProperty.class))
                .collect(Collectors.toList());
        return new ResponseProperties(propertyDtos.size(), propertyDtos);
    }
    // is it same as delete ????

//    @Override
    public ResponseProperties filterPropertiesByCriteria(String street, String city, String state, String zip,
                                                         String country, PropertyTypeEnum propertyType, Integer roomNum,
                                                         Double propertyPrice) {
        List<Property> properties = propertyRepo.filterPropertiesByCriteria(street, city, state, zip, country, propertyType, roomNum, propertyPrice);
        List<ResponseProperty> propertyDtos = properties.stream()
                .map(p -> adapter.convertObject(p, ResponseProperty.class))
                .collect(Collectors.toList());
        return new ResponseProperties(propertyDtos.size(), propertyDtos);
    }
}
