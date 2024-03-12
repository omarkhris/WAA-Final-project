package com.miu.pmtbackendapi.domain.property;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.pmtbackendapi.domain.address.Address;
import com.miu.pmtbackendapi.domain.enums.PropertyStatusEnum;
import com.miu.pmtbackendapi.domain.image.PropertyImage;
import com.miu.pmtbackendapi.domain.propertydetail.PropertyDetail;
import com.miu.pmtbackendapi.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @SequenceGenerator(name = "prop_sq", initialValue = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prop_sq")
    private Long propertyId;

    @Enumerated(EnumType.STRING)
    PropertyStatusEnum statusEnum;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    PropertyDetail propertyDetail;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "property")
    @Fetch(FetchMode.JOIN)
//    @JsonManagedReference
    @JoinTable(name = "property_propimage",
            joinColumns = {@JoinColumn(name = "prop_id")},
            inverseJoinColumns = {@JoinColumn(name = "prop_image_id")}
    )
    List<PropertyImage> propertyImages;

    //    @ManyToMany(mappedBy = "property")
//    List<FavouriteProperty> favouriteProperty;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;


}
