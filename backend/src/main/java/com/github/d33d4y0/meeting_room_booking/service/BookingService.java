package com.github.d33d4y0.meeting_room_booking.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.github.d33d4y0.meeting_room_booking.entity.Booking;
import com.github.d33d4y0.meeting_room_booking.entity.Room;
import com.github.d33d4y0.meeting_room_booking.model.response.BookingResponse;
import com.github.d33d4y0.meeting_room_booking.repository.BookingRepository;
import com.github.d33d4y0.meeting_room_booking.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingService {
    
    private final BookingRepository bookingRepo;

    public void deleteBooking(Long bookingId){
        bookingRepo.deleteById(bookingId);
    }

    public List<BookingResponse> getAllBooking(Long userId){
        List<Booking> allBooking = bookingRepo.findAllByUserId(userId);
        return allBooking.stream().map(BookingResponse::new).toList();
    }
}
