package com.miu.pmtbackendapi.service.user;

import com.miu.pmtbackendapi.domain.user.request.ForgotPassword;
import com.miu.pmtbackendapi.domain.user.response.ResetResponse;
import com.miu.pmtbackendapi.domain.user.response.Users;
import com.miu.pmtbackendapi.domain.user.request.UserDTO;
import com.miu.pmtbackendapi.domain.user.response.UserResponse;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;

import java.util.Optional;

public interface UserService {

    Users getAllUsers(String role);

    Optional<UserResponse> getUser(Long id);

    UserResponse saveUser(UserDTO userDTO);

    Boolean deleteUser(Long userId);

    UserResponse updateUser(UserDTO userDTO) throws ItemNotFoundException;

    ResetResponse forgotPassword(ForgotPassword fgDTO) throws ItemNotFoundException;

    ResetResponse forgotPasswordAdmin(ForgotPassword fgDTO) throws ItemNotFoundException;
}
