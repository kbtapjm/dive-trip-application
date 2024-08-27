package io.divetrip.secuity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.divetrip.enumeration.DiveTripError;
import io.divetrip.exception.dto.ExceptionResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();

            if (StringUtils.equals(message, DiveTripError.UNKNOWN_TOKEN_VALUE.getMessage())) {
                this.setResponse(response, DiveTripError.UNKNOWN_TOKEN_VALUE);
            } else if (StringUtils.equals(message, DiveTripError.INVALID_TOKEN_VALUE.getMessage())) {
                this.setResponse(response, DiveTripError.INVALID_TOKEN_VALUE);
            } else if (StringUtils.equals(message, DiveTripError.EXPIRED_TOKEN_VALUE.getMessage())) {
                this.setResponse(response, DiveTripError.UNKNOWN_TOKEN_VALUE);
            } else if (StringUtils.equals(message, DiveTripError.UNSUPPORTED_TOKEN_VALUE.getMessage())) {
                this.setResponse(response, DiveTripError.UNSUPPORTED_TOKEN_VALUE);
            }
        }
    }

    private void setResponse(HttpServletResponse response, DiveTripError diveTripError) throws IOException {
        ExceptionResponse exceptionResponse = ExceptionResponse.of(diveTripError.getCode(), diveTripError.getMessage(), diveTripError.getStatus());

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(exceptionResponse.getStatus());
        response.getWriter().write(new ObjectMapper().writeValueAsString(exceptionResponse));
    }

}
