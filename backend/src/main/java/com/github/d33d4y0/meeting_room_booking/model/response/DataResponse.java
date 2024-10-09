package com.github.d33d4y0.meeting_room_booking.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse<T> {
    
    private List<T> data;
}
