package com.port.folio.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 실패시, 호출되는 메소드
 * 로그인 실패 횟수 체크, 보안 처리
 * (로그인 실패 5회 누적 시, 본인인증 등 요청)
 */
@Slf4j
@Component
public class LoginFailureHAndler implements AuthenticationFailureHandler{
    
    @Override
    public void onAuthenticationFailure( HttpServletRequest request
                                       , HttpServletResponse response
                                       , AuthenticationException exception) throws IOException, ServletException {
        log.info("로그인 실패;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        response.sendRedirect("/main/login?error=true");

    }
    
}
