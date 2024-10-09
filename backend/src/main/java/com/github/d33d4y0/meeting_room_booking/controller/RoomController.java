package com.github.d33d4y0.meeting_room_booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.d33d4y0.meeting_room_booking.constant.CustomHeader;
import com.github.d33d4y0.meeting_room_booking.model.MutableHttpServletRequest;
import com.github.d33d4y0.meeting_room_booking.model.request.BookingRequest;
import com.github.d33d4y0.meeting_room_booking.model.request.CreateRoomRequest;
import com.github.d33d4y0.meeting_room_booking.model.response.DataResponse;
import com.github.d33d4y0.meeting_room_booking.model.response.RoomResponse;
import com.github.d33d4y0.meeting_room_booking.service.RoomService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService service;

    @GetMapping()
    public ResponseEntity<DataResponse<RoomResponse>> getRooms(
            @RequestParam(required = false, value = "date") String date,
            @RequestParam(required = false, value = "start_time") String startTime,
            @RequestParam(required = false, value = "end_time") String endTime,
            @RequestParam(required = false, value = "capacity") Integer numberOfGuest,
            @RequestHeader(value = "Authorization", required = false) String token) {
        List<RoomResponse> availableRoom = null;
        if (date != null && startTime != null && endTime != null && numberOfGuest != null) {
            availableRoom = service.searchAvailableRoom(date, startTime, endTime, numberOfGuest);
            return ResponseEntity.ok(new DataResponse<RoomResponse>(availableRoom));
        } else if (date != null || startTime != null || endTime != null || numberOfGuest != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing search parameter");
        } else {
            availableRoom = service.getAllRoom();
        }

        return ResponseEntity.ok(new DataResponse<RoomResponse>(availableRoom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable("id") Long roomId,
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.getRoomById(roomId));
    }

    @PostMapping()
    public ResponseEntity<RoomResponse> createRoom(@RequestBody CreateRoomRequest req,
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRoom(req));
    }

    @PostMapping("/{id}/book")
    public Long bookRoom(@PathVariable("id") Long roomId, @RequestBody BookingRequest req,
            @RequestHeader(value = "Authorization", required = false) String token,
            HttpServletRequest request) {
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        Long userId = Long.valueOf(mutableRequest.getHeader(CustomHeader.X_USER_ID.getValue()));
        return service.bookRoom(roomId, userId, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") Long roomId,
            @RequestHeader(value = "Authorization", required = false) String token) {
        service.deleteRoom(roomId);
        return ResponseEntity.accepted().build();
    }
}