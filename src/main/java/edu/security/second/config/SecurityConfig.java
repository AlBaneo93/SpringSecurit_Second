package edu.security.second.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] permitPaths = {"/", "/signup", "/signin", "/my"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().disable();

    http
        .authorizeRequests().antMatchers(permitPaths).permitAll()
        .anyRequest().authenticated();


    http
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();

    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").invalidateHttpSession(true);

    http.exceptionHandling()
        .accessDeniedPage("/denied");
  }

  /*
   * 비밀번호 암/복호화 로직 객체
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }
}
