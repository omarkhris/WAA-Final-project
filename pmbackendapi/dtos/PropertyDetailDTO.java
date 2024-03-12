package com.miu.pmtbackendapi.dtos;

import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.HomeConditionEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDetailDTO {
    PropertyTypeEnum propertyType;
    private Float lotSize;
    private Float homeSize;
    Address address;
    Date yearBuild;
    private Integer roomNum;
    private Integer fullBathroomNum;
    private Integer partialBathroomNum;
    private Double propertyPrice;
    private Double mortgageBalance;
    private HomeConditionEnum homeCondition;
    private Boolean hasTenant;

}
