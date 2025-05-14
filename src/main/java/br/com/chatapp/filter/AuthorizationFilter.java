package br.com.chatapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
    filterName = "AuthFilter",
    urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession httpSession = req.getSession(false);
    String uri = req.getRequestURI();

    if (uri.contains("login.xhtml") || uri.contains("javax.faces.resource")) {
      chain.doFilter(request, response);
      return;
    }

    if (httpSession != null && httpSession.getAttribute("user") != null) {
      chain.doFilter(request, response);
    } else {
      res.sendRedirect(req.getContextPath() + "/login.xhtml");
    }
  }
}
