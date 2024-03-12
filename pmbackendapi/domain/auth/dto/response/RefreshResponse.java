package com.miu.pmtbackendapi.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshResponse {
    private String accessToken;
    private String refreshToken;
}
