package edu.security.second.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * TODO: @Component : Bean을 갖는 클래스를 지정?? (메모 참조)
 * EntryPoint : AuthenticationEntiryPoint를 구현하여 인증에 실패한 사용자의 response에
 * HttpServletResponse.SC_UNAUTHORIZED를 담아주도록 구현
 * */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
  }
}
