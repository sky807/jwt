package com.jwt.project.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 토큰이 인증되지 않았을 경우 (로그인실패)
 * 인증 실패시 처리되는 곳으로
 * 즉, 유저정보 없이 접근하면 이쪽으로 온다.
 *
 * @author 송하늘
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
   }
}
