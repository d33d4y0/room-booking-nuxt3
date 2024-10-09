package com.github.d33d4y0.meeting_room_booking.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.meeting_room_booking.constant.CustomHeader;
import com.github.d33d4y0.meeting_room_booking.model.MutableHttpServletRequest;
import com.github.d33d4y0.meeting_room_booking.model.response.BookingResponse;
import com.github.d33d4y0.meeting_room_booking.model.response.DataResponse;
import com.github.d33d4y0.meeting_room_booking.service.BookingService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Long bookingId,
            @RequestHeader(value = "Authorization", required = false) String token,
            HttpServletRequest request) {
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        Long userId = Long.valueOf(mutableRequest.getHeader(CustomHeader.X_USER_ID.getValue()));
        service.deleteBooking(bookingId);
    }

    @GetMapping()
    public DataResponse<BookingResponse> getAllBooking(HttpServletRequest request) {
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        Long userId = Long.valueOf(mutableRequest.getHeader(CustomHeader.X_USER_ID.getValue()));
        return new DataResponse<BookingResponse>(service.getAllBooking(userId));
    }

}
