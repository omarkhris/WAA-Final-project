package com.miu.pmtbackendapi.service.auth.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miu.pmtbackendapi.domain.auth.UserRole;
import com.miu.pmtbackendapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<UserRole> userRoles;

    public CustomUserDetails(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userRoles = user.getUserRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
                .map((userRole -> new SimpleGrantedAuthority(userRole.getUser_role())))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
