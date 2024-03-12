package com.miu.pmtbackendapi.domain.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.miu.pmtbackendapi.domain.property.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyImage {

    @Id
    @SequenceGenerator(name = "prop_image_sq", initialValue = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "prop_image_sq")
    private Long pImageId;
    private String imageName;
    private String imageLocation;
//    private Long imageSize;
//    private byte[] imageByte;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private Property property;

}
