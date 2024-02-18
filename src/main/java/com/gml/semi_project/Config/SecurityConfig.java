package com.gml.semi_project.Config;

import com.gml.semi_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    // 로그인하지 않은 유저들만 접근 가능한 URL
    private static final String[] anonymousUserUrl = {"/users/login", "/users/join"};

    // 로그인한 유저들만 접근 가능한 URL
    private static final String[] authenticatedUserUrl = {"/boards/**/**/edit", "/boards/**/**/delete", "/likes/**", "/users/myPage/**", "/users/edit", "/users/delete"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(anonymousUserUrl).anonymous()
                                .requestMatchers(authenticatedUserUrl).authenticated()
                                .requestMatchers("/boards/greeting/write").hasAnyAuthority("NEWBIE", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/boards/greeting").hasAnyAuthority("NEWBIE", "ADMIN")
                                .requestMatchers("/boards/free/write").hasAnyAuthority("NEWBIE", "VIP", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/boards/free").hasAnyAuthority("NEWBIE", "VIP", "ADMIN")
                                .requestMatchers("/boards/vip/**").hasAnyAuthority("VIP", "ADMIN")
                                .requestMatchers("/users/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/comments/**").hasAnyAuthority("NEWBIE", "VIP", "ADMIN")
                                .anyRequest().permitAll()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(new MyAccessDeniedHandler(userRepository)) // 인가 실패
                                .authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 인증 실패
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login") // 로그인 페이지
                                .usernameParameter("memberId") // 로그인에 사용될 id
                                .passwordParameter("password") // 로그인에 사용될 password
                                .failureUrl("/users/login?fail") // 로그인 실패 시 redirect 될 URL => 실패 메세지 출력
                                .successHandler(new MyLoginSuccessHandler(userRepository)) // 로그인 성공 시 실행 될 Handler
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout") // 로그아웃 URL
                                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                );
        return httpSecurity.build();
    }
}