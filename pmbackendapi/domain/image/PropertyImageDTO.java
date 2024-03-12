package com.miu.pmtbackendapi.domain.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImageDTO {

    private Long pImageId;
    private String imageName;
    private String imageLocation;
}
