package com.github.d33d4y0.meeting_room_booking.model.response;

import com.github.d33d4y0.meeting_room_booking.entity.Booking;

import lombok.Data;

@Data
public class BookingResponse {
    private Long id;
    private String date;
    private String startTime;
    private String endTime;
    private Long roomId;
    private String roomName;
    private int maxCapacity;
    private String status;

    public BookingResponse(Booking bookingEntity) {
        this.id = bookingEntity.getId();
        this.date = bookingEntity.getStartTime().toLocalDate().toString();
        this.startTime = bookingEntity.getStartTime().toLocalTime().toString();
        this.endTime = bookingEntity.getEndTime().toLocalTime().toString();
        this.roomId = bookingEntity.getRoom().getId();
        this.status = bookingEntity.getStatus().name();
        this.roomName = bookingEntity.getRoom().getName();
        this.maxCapacity = bookingEntity.getRoom().getMaxCapacity();
    }
}
