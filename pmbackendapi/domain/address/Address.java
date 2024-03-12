package com.miu.pmtbackendapi.domain.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.pmtbackendapi.domain.property.Property;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @SequenceGenerator(name = "address_sq", initialValue = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_sq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//    @SequenceGenerator(name = "my_seq", sequenceName = "my_sequence", allocationSize = 1)
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
