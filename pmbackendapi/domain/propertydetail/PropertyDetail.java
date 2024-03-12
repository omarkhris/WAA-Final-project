package com.miu.pmtbackendapi.domain.propertydetail;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.HomeConditionEnum;
import com.miu.pmtbackendapi.domain.enums.PropertyTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDetail {
    @Id
    @SequenceGenerator(name = "prop_detail_sq", initialValue = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prop_detail_sq")
    private Long pdId;

    @Enumerated(EnumType.STRING)
    PropertyTypeEnum propertyType;
    private Double lotSize;
    private Double homeSize;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Address address;

//    @Temporal(TemporalType.DATE)
    String yearBuild;

    private Integer roomNum;
    private Integer fullBathroomNum;
    private Integer partialBathroomNum;
    private Double propertyPrice;
    private Double mortgageBalance;

    @Enumerated(EnumType.STRING)
    private HomeConditionEnum homeCondition;

    private Boolean hasTenant;


}
