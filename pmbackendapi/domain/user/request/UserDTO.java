package com.miu.pmtbackendapi.domain.user.request;

import com.miu.pmtbackendapi.domain.auth.UserRole;
import com.miu.pmtbackendapi.domain.enums.UserStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatus;

    private Collection<UserRole> userRoles;
}
