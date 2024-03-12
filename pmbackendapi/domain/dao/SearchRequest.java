package com.miu.pmtbackendapi.domain.dao;


import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {


    private Address address;
    private PropertyDetail propertyDetail;

    private PropertyStatusEnum statusEnum;

}
