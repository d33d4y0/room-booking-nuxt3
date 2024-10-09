package com.github.d33d4y0.meeting_room_booking.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    
    private String accessToken;
    private String refreshToken;
}
