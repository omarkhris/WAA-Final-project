package com.miu.pmtbackendapi.domain.user.request;

import lombok.Data;

@Data
public class ForgotPassword {
    private String email;
    private String psw;
}
