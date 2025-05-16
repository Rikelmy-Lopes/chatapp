package br.com.chatapp.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.chatapp.security.JwtService;
import br.com.chatapp.service.CookieService;

@WebFilter(
    filterName = "AuthFilter",
    urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {
  @Inject private JwtService jwtService;
  @Inject private CookieService cookieService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    String uri = req.getRequestURI();
    Cookie cookie = this.cookieService.get(req, "jwt_token");

    boolean isLoggedIn = cookie != null ? this.jwtService.verify(cookie.getValue()) != null : false;
    boolean isLoginPage = uri.contains("login.xhtml");
    boolean isRegisterPage = uri.contains("register.xhtml");
    boolean isResourceRequest = uri.contains("javax.faces.resource");

    if ((isLoginPage || isRegisterPage) && isLoggedIn) {
      res.sendRedirect(req.getContextPath() + "/chat.xhtml");
    } else if (isLoginPage || isRegisterPage || isResourceRequest || isLoggedIn) {
      chain.doFilter(request, response);
    } else {
      res.sendRedirect(req.getContextPath() + "/login.xhtml");
    }
  }
}
