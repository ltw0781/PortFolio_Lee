package com.port.folio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.port.folio.security.CustomAccessDeniedHandler;
import com.port.folio.security.LoginFailureHAndler;
import com.port.folio.security.LoginSuccessHandler;
import com.port.folio.user.service.UserDetailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    private LoginSuccessHandler LoginSuccessHandler;

    @Autowired
    private LoginFailureHAndler loginFailureHAndler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 기본적으로 로그인 페이지가 제공이 되지 않음
     * 
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        // 인가 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                .requestMatchers("/user", "/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers( "/main/**", "/board/**", "/comment/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().permitAll());

        // 폼 로그인 설정
        // http.formLogin( login -> login.permitAll());

        // 커스텀 로그인 페이지 지정
        http.formLogin(login -> login
                // .usernameParameter("id") // 아이디 파라미터
                // .passwordParameter("pw") // 비밀번호 파라미터
                .loginPage("/main/login") // 로그인 페이지 경로
                .loginProcessingUrl("/main/login") // 로그인 처리 경로
                // .defaultSuccessUrl("/?login=true") // 로그인 성공 후 리다이렉트 경로
                .successHandler(LoginSuccessHandler) // 로그인 성공 핸들러 설정
                .failureHandler(loginFailureHAndler) // 로그인 실패 핸들러 설정
        );

        http.exceptionHandling(exception -> exception
                // 예외 처리 페이지 설정
                .accessDeniedPage("/exception")
                // 접근 거부 핸들러 설정
                .accessDeniedHandler(customAccessDeniedHandler));

        // 사용자 정의 인증
        http.userDetailsService(userDetailsServiceImpl);

        // 자동 로그인
        http.rememberMe(me -> me
                .key("shopping")
                .tokenRepository(tokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 7));

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/main/logout") // 로그아웃 요청 경로
                .logoutSuccessUrl("/main/login?logout=true") // 로그아웃 성공 시 URL
                .invalidateHttpSession(true) // 세션 초기화
        // .deleteCookies("remember-id") // 로그아웃 시, 아이디저장 쿠키 삭제
        // .logoutSuccessHandler(null) // 로그아웃 성공 핸들러 설정
        );

        return http.build();

    }

    /**
     * 🍃 자동 로그인 저장소 빈 등록
     * ✅ 데이터 소스
     * ⭐ persistent_logins 테이블 생성
     * create table persistent_logins (
     * username varchar(64) not null
     * , series varchar(64) primary key
     * , token varchar(64) not null
     * , last_used timestamp not null
     * );
     * 🔄 자동 로그인 프로세스
     * ✅ 로그인 시
     * ➡ 👩‍💼(ID, 시리즈, 토큰) 저장
     * ✅ 로그아웃 시,
     * ➡ 👩‍💼(ID, 시리즈, 토큰) 삭제
     * 
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        // JdbcTokenRepositoryImpl : 토큰 저장 데이터 베이스를 등록하는 객체
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        // ✅ 토큰 저장소를 사용하는 데이터 소스 지정
        // - 시큐리티가 자동 로그인 프로세스를 처리하기 위한 DB를 지정합니다.
        repositoryImpl.setDataSource(dataSource);
        // persistent_logins 테이블 생성
        try {
            repositoryImpl.getJdbcTemplate().execute(JdbcTokenRepositoryImpl.CREATE_TABLE_SQL);
        } catch (BadSqlGrammarException e) {
            log.error("persistent_logins 테이블이 이미 존재합니다.");
        } catch (Exception e) {
            log.error("자동 로그인 테이블 생성 중 , 예외 발생");
        }
        return repositoryImpl;
    }

    // /**
    // * 인메모리 인증방식
    // * @return
    // */
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails admin = User.builder()
    // .username("admin") // 사용자 이름
    // // .password("{noop}123456") // 비밀번호 (noop: 평문 처리)
    // .password(passwordEncoder.encode("123456"))
    // .roles("USER", "ADMIN") // ROLE_ADMIN 권한
    // .build();

    // UserDetails user = User.builder()
    // .username("user")
    // // .password("{noop}123456")
    // .password(passwordEncoder.encode("123456"))
    // .roles("USER") // ROLE_USER 권한
    // .build();
    // // 인메모리 방식 인증
    // return new InMemoryUserDetailsManager(admin, user);
    // }

    // JDBC 인증 방식
    // ✅ 데이터 소스 (URL, ID, PW) - application.properties
    // ✅ SQL 쿼리 등록
    // ⭐ 사용자 인증 쿼리
    // ⭐ 사용자 권한 쿼리
    // @Bean
    // public UserDetailsService userDetailsService() {
    // JdbcUserDetailsManager userDetailsManager = new
    // JdbcUserDetailsManager(dataSource);

    // // 사용자 인증 쿼리
    // String sql1 = " SELECT username, password, enabled "
    // + " FROM user "
    // + " WHERE username = ? "
    // ;
    // // 사용자 권한 쿼리
    // String sql2 = " SELECT username, auth "
    // + " FROM user_auth "
    // + " WHERE username = ? "
    // ;
    // userDetailsManager.setUsersByUsernameQuery(sql1);
    // userDetailsManager.setAuthoritiesByUsernameQuery(sql2);
    // return userDetailsManager;
    // }

    /**
     * AuthenticationManager 인증 관리자 빈 등록
     * 
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

}
