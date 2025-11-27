package com.port.folio.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.port.folio.user.domain.CustomUser;
import com.port.folio.user.domain.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    @GetMapping("")
    // public String main(Principal principal, Model model) throws Exception {
    // public String main(Authentication authentication, Model model) throws Exception {
    public String main(@AuthenticationPrincipal CustomUser authUser, Model model) throws Exception {

        log.info(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;메인화면;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");

        // if (principal != null) {
        // String username = principal.getName(); // 인증된 사용자 아이디
        // log.info("username : {}", username);
        // Users user = userService.select(username); // 사용자 정보 조회
        // log.info("user : {}", user);
        // model.addAttribute("user", user); // 사용자 정보 모델에 등록
        // }

        // if (authentication != null) {
        // User user = (User) authentication.getPrincipal();
        // String username = user.getUsername(); // 인증된 사용자 아이디
        // String password = user.getPassword(); // 인증된 사용자 비밀번호
        // Collection<GrantedAuthority> authList = user.getAuthorities(); // 사용자 권한
        // log.info("username : {}", username);
        // log.info("password : {}", password);
        // log.info("authList : {}", authList);

        // Users joinedUser = userService.select(username); // 사용자 정보 조회
        // log.info("joinedUser : {}", joinedUser);

        // model.addAttribute("user", user); // 사용자 정보 모델에 등록

        // }

        if (authUser != null) {

            log.info("authUser : {}", authUser);
            Users user = authUser.getUser();
            model.addAttribute("user", user);

        }

        return "index";
    }

}
