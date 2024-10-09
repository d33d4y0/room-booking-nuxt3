package com.github.d33d4y0.meeting_room_booking.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.d33d4y0.meeting_room_booking.constant.CustomHeader;
import com.github.d33d4y0.meeting_room_booking.model.MutableHttpServletRequest;
import com.github.d33d4y0.meeting_room_booking.service.AccessTokenServiceImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomHeaderFilter implements Filter {

    private static final List<String> EXCLUDED_URLS = Arrays.asList("/api/v1/auth",
            "/doc",
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-ui",
            "api-docs",
            "/error");
    private final AccessTokenServiceImpl jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest((HttpServletRequest) request);

        String requestURI = httpRequest.getRequestURI();
        if (shouldFilter(requestURI)) {
            String jwt = getAccessTokenFromCookie(httpRequest.getCookies());
            final Long userId;
            userId = jwtService.extractUserId(jwt);
            mutableRequest.putHeader(CustomHeader.X_USER_ID.getValue(), userId.toString());
        }
        chain.doFilter(mutableRequest, response);
    }

    private boolean shouldFilter(String requestURI) {
        return EXCLUDED_URLS.stream().noneMatch(requestURI::contains);
    }

    private String getAccessTokenFromCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
