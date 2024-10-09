package com.github.d33d4y0.meeting_room_booking.model;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class MutableHttpServletRequest extends HttpServletRequestWrapper  {

    private final Map<String, String> customHeaders = new HashMap<String, String>();
 
    public MutableHttpServletRequest(HttpServletRequest request){
        super(request);
    }
    
    public void putHeader(String name, String value){
        this.customHeaders.put(name, value);
    }
 
    @Override
    public String getHeader(String name) {
        String headerValue = customHeaders.get(name);
        
        if (headerValue != null){
            return headerValue;
        }
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }
 
    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet<String>(customHeaders.keySet());
        
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            String n = e.nextElement();
            set.add(n);
        }
 
        return Collections.enumeration(set);
    }
    
}
