package com.github.d33d4y0.meeting_room_booking.model.response;

import com.github.d33d4y0.meeting_room_booking.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private String email;
    private String firstName;
    private String lastName;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
