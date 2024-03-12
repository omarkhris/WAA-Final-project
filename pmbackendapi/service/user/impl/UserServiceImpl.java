package com.miu.pmtbackendapi.service.user.impl;

import com.miu.pmtbackendapi.domain.user.request.ForgotPassword;
import com.miu.pmtbackendapi.domain.user.response.ResetResponse;
import com.miu.pmtbackendapi.domain.user.response.Users;
import com.miu.pmtbackendapi.exception.customexception.ItemNotFoundException;
import com.miu.pmtbackendapi.service.commonadpater.Adapter;
import com.miu.pmtbackendapi.service.user.adapter.UserAdapter;
import com.miu.pmtbackendapi.domain.user.User;
import com.miu.pmtbackendapi.domain.user.request.UserDTO;
import com.miu.pmtbackendapi.domain.user.response.UserResponse;
import com.miu.pmtbackendapi.repo.user.UserRepository;
import com.miu.pmtbackendapi.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAdapter userAdapter;
    private final Adapter adapter;

    @Override
    public Users getAllUsers(String role) {
        List<User> users;
        if (role == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.finAllCustomers(role);
        }
        return userAdapter.getResponsesForAllUsers(users);
    }

    @Override
    public Optional<UserResponse> getUser(Long id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return Optional.of(adapter.convertObject(oUser.get(), UserResponse.class));
        } else {
            return Optional.empty();
        }
    }


    @Override
    public UserResponse saveUser(UserDTO userDTO) {
        User user = adapter.convertObject(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return adapter.convertObject(saved, UserResponse.class);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserResponse updateUser(UserDTO userDTO) throws ItemNotFoundException {
        Optional<User> oUser = userRepository.findById(userDTO.getUserId());
        if (oUser.isPresent()) {
            User user = adapter.convertObject(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User updated = userRepository.save(user);
            return adapter.convertObject(updated, UserResponse.class);
        }
        throw new ItemNotFoundException("User not found, unable to update the request.");
    }

    @Override
    public ResetResponse forgotPassword(ForgotPassword fgDTO) throws ItemNotFoundException {
        Optional<User> oUser = userRepository.findAUserByEmail(fgDTO.getEmail());
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setPassword(passwordEncoder.encode(fgDTO.getPsw()));
            userRepository.save(user);

            return new ResetResponse(user.getEmail(), "Password reset successfully.");
        }

        throw new ItemNotFoundException("User not found");
    }

    @Override
    public ResetResponse forgotPasswordAdmin(ForgotPassword fgDTO) throws ItemNotFoundException {
        return forgotPassword(fgDTO);
    }
}
