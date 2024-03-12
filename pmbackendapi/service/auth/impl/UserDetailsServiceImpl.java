package com.miu.pmtbackendapi.service.auth.impl;

import com.miu.pmtbackendapi.domain.enums.UserStatusEnum;
import com.miu.pmtbackendapi.exception.customexception.UserDeactivedException;
import com.miu.pmtbackendapi.repo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        } else if (user.getUserStatus().getValue().equals(UserStatusEnum.valueOf("DEACTIVE").getValue())) {
            try {
                throw new UserDeactivedException("User is Deactivated.");
            } catch (UserDeactivedException e) {
                throw new RuntimeException(e);
            }
        }
        var userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
