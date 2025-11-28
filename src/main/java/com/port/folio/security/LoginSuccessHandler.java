package com.port.folio.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 성공 처리 이벤트 핸들러
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    
    /**
     *  로그인 성공시 호출되는 메소드
     *  아이디 저장 쿠키 생성
     * 
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

                log.info("로그인 성공!!!!!!!!!!!!!!!!!");

                // 아이디 저장
                String rememberId = request.getParameter("remember-id");
                String username = request.getParameter("username");

                //  아이디 저장 체크
                if ( rememberId != null && rememberId.equals("on") ) {
                    
                    Cookie cookie = new Cookie("remember-id", username);
                    cookie.setMaxAge( 60 * 60 * 24 * 7 );
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }

                // 아이디 저장 체크 안함
                else {
                    Cookie cookie = new Cookie("remember-id", username);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }


                super.onAuthenticationSuccess(request, response, authentication);

    }
    
    

}
