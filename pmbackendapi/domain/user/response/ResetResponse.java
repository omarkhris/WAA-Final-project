package com.miu.pmtbackendapi.domain.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetResponse {
    private String email;
    private String message;
}
