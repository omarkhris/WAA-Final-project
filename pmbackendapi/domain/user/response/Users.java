package com.miu.pmtbackendapi.domain.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Users {
    private int userCount;
    private List<UserResponse> users;
}
