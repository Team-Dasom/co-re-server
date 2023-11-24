package com.dongyang.core.global.common.filter.exception;


import com.dongyang.core.global.common.exception.model.CustomException;
import com.dongyang.core.global.common.exception.model.IllegalArgumentException;
import com.dongyang.core.global.common.exception.model.RateLimitException;
import com.dongyang.core.global.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class ExceptionHandlerFilter implements Filter {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (RateLimitException | IllegalArgumentException exception) {
            setErrorResponse((HttpServletResponse) response, exception);
        }
    }

    private void setErrorResponse(HttpServletResponse response, CustomException exception) throws IOException {
        ApiResponse<Object> errorResponse = ApiResponse.error(exception.getErrorCode());

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exception.getErrorCode().getHttpStatusCode());
    }
}
