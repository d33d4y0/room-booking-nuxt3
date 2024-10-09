package com.github.d33d4y0.meeting_room_booking.model.response;

import com.github.d33d4y0.meeting_room_booking.entity.Room;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomResponse {
    
    private Long roomId;
    private String roomName;
    private Integer maxCapacity;

    public RoomResponse(Room room){
        this.roomId = room.getId();
        this.roomName = room.getName();
        this.maxCapacity = room.getMaxCapacity();
    }
}
