package edu.security.second.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // 인증 요청을 할 경로
  private static final String[] permitPaths = {"/", "/signup", "/signin", "/my"};

  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private UserDetailsService userDetailsService;

  private JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 추가 설정
    http
        .csrf().disable()
        .cors().disable();

    // 인증/인가 설정
    http
        .authorizeRequests().antMatchers(permitPaths).permitAll()
        .anyRequest().authenticated();

    // 로그인 설정
    http
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();

    // 로그아웃 설정
    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").invalidateHttpSession(true);


    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint);


    http
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  /*
   * 비밀번호 암/복호화 로직 객체
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }
}
