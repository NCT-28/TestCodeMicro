package com.spring.base.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@UtilityClass
public class ErrorUtils {

    /**
     * Gửi lỗi đến client
     *
     * @param s HttpServletResponse
     * @param e thông tin lỗi
     * @throws IOException lỗi khi không thể send
     */
    public void send(ServletResponse s, String e) throws IOException {
        // Ép kiểu
        HttpServletResponse h = (HttpServletResponse) s;
        // Gửi lỗi
        h.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
        h.setStatus(HttpStatus.BAD_REQUEST.value());
        h.setContentType("application/json;charset=utf-8");
        h.getWriter().write("{\"message\":\"" + e + "\"}");
        h.getWriter().flush();
        h.getWriter().close();
    }
}