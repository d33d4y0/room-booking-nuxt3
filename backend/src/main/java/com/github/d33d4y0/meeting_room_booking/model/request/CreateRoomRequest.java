package com.github.d33d4y0.meeting_room_booking.model.request;

import lombok.Data;

@Data
public class CreateRoomRequest {
    
    private String roomName;
    private Integer maxCapacity;
}
