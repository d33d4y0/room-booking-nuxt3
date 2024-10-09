package com.github.d33d4y0.meeting_room_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.d33d4y0.meeting_room_booking.entity.Booking;

public interface BookingRepository  extends JpaRepository<Booking, Long> {
    
    public List<Booking> findAllByUserId(Long userId);
}
