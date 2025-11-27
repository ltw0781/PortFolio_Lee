package com.port.folio.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.port.folio.user.domain.UserAuth;
import com.port.folio.user.domain.Users;
import com.port.folio.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 회원가입
     * 1. 비밀번호 암호화
     * 2. 회원등록
     * 3. 기본 권한 등록
     */
    @Override
    @Transactional                                                      // 트랜잭션 처리를 설정 ( 회원정보, 회원권한 )
    public int join(Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);      // 비밀번호 암호화
        user.setPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if (result > 0) {
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUsername(username);
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);
            
        }

        return result;


    }

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {

        int result = userMapper.insertAuth(userAuth);
        return result;

    }

    @Override
    public boolean login(Users user, HttpServletRequest request) {
        // 토큰을 생성 해주자
        String username = user.getName();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);


        // 토큰을 이용하여 인증
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증여부 확인
        boolean result = authentication.isAuthenticated();

        // 인증에 성공하면 SecurityContext 에 설정
        if ( result ) {
            SecurityContext SecurityContext = SecurityContextHolder.getContext();
            SecurityContext.setAuthentication(authentication);

            // 세션 인증 정보 설정 ( 세션이 없으면 새로 생성 )
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContext);
        }
        return result;
    }

    @Override
    public Users select(String username) throws Exception {

        Users user = userMapper.select(username);
        return user;

    }
    
}
