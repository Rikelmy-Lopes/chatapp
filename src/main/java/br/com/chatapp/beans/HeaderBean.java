package br.com.chatapp.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import br.com.chatapp.service.CookieService;

@Named("headerBean")
@SessionScoped
public class HeaderBean implements Serializable {

  private static final long serialVersionUID = 1L;
  @Inject private FacesContext facesContext;
  @Inject private CookieService cookieService;

  public String logOut() {
    System.out.println("Fazendo Logout!!!");
    this.facesContext.getExternalContext().invalidateSession();
    HttpServletResponse res =
        (HttpServletResponse) this.facesContext.getExternalContext().getResponse();
    this.cookieService.remove(res, "jwt_token");

    return "login?faces-redirect=true";
  }
}
