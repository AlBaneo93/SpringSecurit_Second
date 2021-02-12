package edu.security.second.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // 인증 요청을 할 경로
  private static final String[] permitPaths = {"/authenticate", "/api/v1/users"};

  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 추가 설정
    http
        .httpBasic().disable()
        .csrf().disable()
        .cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // 인증/인가에 대한 경로 설정
    http
        .authorizeRequests().antMatchers(permitPaths).permitAll()
        .anyRequest().authenticated();

    // 로그인 설정(REST를 사용하지 않을 때)
//    http
//        .formLogin()
//        .loginPage("/login")
//        .defaultSuccessUrl("/")
//        .permitAll();

    // 로그아웃 설정
    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").invalidateHttpSession(true);


    // 에러가 발생했을때 사용할 EntryPoint
    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint);

    http
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

  }


}