package com.github.d33d4y0.meeting_room_booking.constant;

public enum CustomHeader {
    
    X_USER_ID("X-User-ID");

    private final String value;

    CustomHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
