package com.miu.pmtbackendapi.domain.user.response;

import com.miu.pmtbackendapi.domain.property.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<Property> properties;
}
