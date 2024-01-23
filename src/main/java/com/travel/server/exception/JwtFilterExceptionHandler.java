package com.travel.server.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.exception.model.ErrorCode;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilterExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    )
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            setErrorResponse(request, response, e.getErrorCode());
        } catch (Exception e){
            setErrorResponse(request, response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, ErrorCode e) throws IOException {
        response.setStatus(e.getStatus());
        response.setContentType("application/json; charset=UTF-8");

        log.info("=== JwtFilterExceptionHandler code: {}, message: {}", e.getCode(), e.getMessage());

        String jsonResponse = new ObjectMapper().writeValueAsString(
                CommonResponse.fail(e)
        );

        response.getWriter().write(jsonResponse);
    }
}
