package com.github.d33d4y0.meeting_room_booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.d33d4y0.meeting_room_booking.model.request.CreateUserRequest;
import com.github.d33d4y0.meeting_room_booking.model.request.SignInRequest;
import com.github.d33d4y0.meeting_room_booking.model.response.SignInResponse;
import com.github.d33d4y0.meeting_room_booking.model.response.UserResponse;
import com.github.d33d4y0.meeting_room_booking.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @PostMapping("/signin")
    public SignInResponse signin(@RequestBody SignInRequest request, HttpServletResponse response) {
        SignInResponse signInResponse = service.signin(request);
        Cookie accessToken = createCookie("accessToken", signInResponse.getAccessToken());
        accessToken.setMaxAge(24 * 60 * 60); 
        response.addCookie(accessToken);

        Cookie refreshToken = createCookie("refreshToken", signInResponse.getRefreshToken());
        refreshToken.setMaxAge(24 * 60 * 60 * 7); 
        response.addCookie(refreshToken);
        return signInResponse;
    }

    @PostMapping("/refresh")
    public SignInResponse signinWithRefresh(HttpServletRequest request, HttpServletResponse response) {
        String reqRefreshToken = getAccessTokenFromCookie(request.getCookies(), "refreshToken");
        SignInResponse signInResponse = service.signinWithRefreshToken(reqRefreshToken);
        Cookie accessToken = createCookie("accessToken", signInResponse.getAccessToken());
        accessToken.setMaxAge(24 * 60 * 60); 
        response.addCookie(accessToken);

        Cookie refreshToken = createCookie("refreshToken", signInResponse.getRefreshToken());
        refreshToken.setMaxAge(24 * 60 * 60 * 7); 
        response.addCookie(refreshToken);
        return signInResponse;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(name + " : " + value);
        }
        String reqRefreshToken = getAccessTokenFromCookie(request.getCookies(), "refreshToken");
        System.out.println("test: " + reqRefreshToken);
        return "";
    }
    
    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        // cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    private String getAccessTokenFromCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
