package com.miu.pmtbackendapi.domain.property.dto.request;

import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.image.PropertyImage;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import com.miu.pmtbackendapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    PropertyStatusEnum statusEnum;

    Address address;

    PropertyDetail propertyDetail;

    List<PropertyImage> propertyImages;

    User Owner;
}
