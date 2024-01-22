package com.travel.server.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.exception.model.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");

        String jsonResponse = new ObjectMapper().writeValueAsString(
                CommonResponse.fail(ErrorCode.MEMBER_NOT_TOKEN)
        );

        response.getWriter().write(jsonResponse);
    }
}
