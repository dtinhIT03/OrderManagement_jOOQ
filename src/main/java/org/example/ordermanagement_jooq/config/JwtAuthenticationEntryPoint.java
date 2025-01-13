package org.example.ordermanagement_jooq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ordermanagement_jooq.data.response.ResponseError;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /* hàm này sẽ đc gọi khi có lỗi xác thực hoặc từ chối truy cập*/
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseError error = new ResponseError(401,"Authentication is require to access this resource");
        // sử dụng ObjectMapper để chuyển đổi đối tượng ResponseError thành chuỗi JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String errorJson = objectMapper.writeValueAsString(error);

        response.getWriter().write(errorJson);

        //đảm bảo rằng phản hồi đã đc gửi đầy đủ
        response.flushBuffer();
    }
}
