package com.travel.server.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.exception.model.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");

        log.info("authException {}, request {}", authException.getMessage(), request.getAttribute("exception"));

        String jsonResponse = new ObjectMapper().writeValueAsString(
                CommonResponse.fail(ErrorCode.MEMBER_NOT_TOKEN)
        );

        response.getWriter().write(jsonResponse);
    }
}
