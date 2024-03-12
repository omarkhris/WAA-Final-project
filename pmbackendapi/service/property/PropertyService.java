package com.miu.pmtbackendapi.service.property;

import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.property.dto.response.ResponseProperties;
import com.miu.pmtbackendapi.domain.property.dto.response.ResponseProperty;
import com.miu.pmtbackendapi.exception.CustomMessage;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PropertyService {

    List<ResponseProperty> getAllProperties();

    ResponseProperty getPropertyById(Long id) throws ItemNotFoundException;

    ResponseProperty createProperty(Property property);

    List<ResponseProperty> getPropertyByOwner(Long ownerId) throws ItemNotFoundException;

    CustomMessage deletePropertyById(Long id);

    ResponseProperty updateProperty(Long id, Property property);

    Boolean cancelProperty(Long id);

    //CHange this to ResponseProperties wrapper oobject
    ResponseProperties getPropertiesByParam(String street, String city, String state, String zip, String country,
                                            PropertyTypeEnum propertyType, Integer roomNum, Double propertyPrice);


    ResponseProperties filterPropertiesByCriteria(String street, String city, String state, String zip, String country,
                                                  PropertyTypeEnum propertyType, Integer roomNum, Double propertyPrice);

    void saveStatusProperty(Property property);

    void cancelStatusProperty(Property property);
}
