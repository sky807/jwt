package com.jwt.project.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인가실패시 처리되는 곳으로
 * 즉, 유저정보는 있으나 접근할 수 있는 권한이 없을 때
 *
 * @author 송하늘
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

   @Override
   public void handle(HttpServletRequest request,
                      HttpServletResponse response,
                      AccessDeniedException accessDeniedException) throws IOException {
      //필요한 권한이 없이 접근하려 할때 403
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
   }
}
