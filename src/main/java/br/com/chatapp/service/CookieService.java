package br.com.chatapp.service;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieService implements Serializable {
  private static final long serialVersionUID = 1L;

  public Cookie get(HttpServletRequest req, String name) {
    if (req.getCookies() == null) {
      return null;
    }
    for (Cookie cookie : req.getCookies()) {
      if (cookie.getName().equals(name)) {
        return cookie;
      }
    }
    return null;
  }

  public void add(
      HttpServletResponse res, String name, String value, boolean isHttpOnly, int expiry) {

    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(isHttpOnly);
    cookie.setMaxAge(expiry);
    res.addCookie(cookie);
  }
}
