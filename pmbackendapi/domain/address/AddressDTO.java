package com.miu.pmtbackendapi.domain.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
