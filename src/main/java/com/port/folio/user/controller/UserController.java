package com.port.folio.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.port.folio.user.domain.Users;
import com.port.folio.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/main")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원가입 화면
     * 
     * @return
     */
    @GetMapping("/join")
    public String join() {
        return "/main/join";
    }

    /**
     * 회원 가입 처리
     * 
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPost(Users user, HttpServletRequest request) throws Exception {

        // 암호화전 비밀번호 호출
        String plainPassword = user.getPassword();
        // 회원가입 요청
        int result = userService.join(user);
        // 회원가입시 바로 로그인
        boolean loginResult = false;
        if (result > 0) {
            // 암호화 전 비밀번호로 다시 세팅
            user.setPassword(plainPassword);
            loginResult = userService.login(user, request); // 바로 로그인
        }
        if (loginResult) {
            // 메인 화면으로 이동
            return "redirect:/";
        }
        if (result > 0) {
            // 로그인 화면으로 이동
            return "redirect:/main/login";
        }
        return "redirect:/main/join?error=true";
    }

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login(@CookieValue(value = "remember-id", required = false) Cookie cookie, Model model) {
        log.info(";;;;;;;;;;;;;;;;;;로그인 페이지;;;;;;;;;;;;;;;;;;");
        String username = "";
        boolean rememberId = false;
        if ( cookie != null ) {
            log.info("CookieName : " + cookie.getName());
            log.info("CookieValue : " + cookie.getValue());
            username = cookie.getValue();
            rememberId = true;
        }
        model.addAttribute("username", username);
        model.addAttribute("rememberId", rememberId);
        return "/main/login";
    }
    

}
