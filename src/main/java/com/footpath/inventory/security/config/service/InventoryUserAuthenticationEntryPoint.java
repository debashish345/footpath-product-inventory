package com.footpath.inventory.security.config.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryUserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        Map<String, String> errorMap = new HashMap<>();

        if(e.getCause() != null){
            errorMap.put("Cause", e.getCause().toString());
            errorMap.put("Message", e.getMessage());
        }else{
            errorMap.put("Message", e.getMessage());
            errorMap.put("Illegal attempt", "true");
        }
        byte[] errorMsg = new ObjectMapper().writeValueAsBytes(errorMap);
        httpServletResponse.getOutputStream().write(errorMsg);
    }
}
