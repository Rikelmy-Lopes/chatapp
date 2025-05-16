package br.com.chatapp.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.chatapp.model.Result;
import br.com.chatapp.model.User;
import br.com.chatapp.service.LoginService;

@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject private FacesContext facesContext;
  @Inject private LoginService loginService;

  private String email = "";
  private String password = "";

  public String login() {
    Result<User, String> result = this.loginService.verifyCredentials(this.email, this.password);

    if (result.isFailure()) {
      this.facesContext.addMessage(
          null,
          new FacesMessage(
              FacesMessage.SEVERITY_ERROR, "Error ao fazer login!", result.getError()));
      return "login";
    }

    HttpSession httpSession =
        (HttpSession) this.facesContext.getExternalContext().getSession(false);
    httpSession.setAttribute("user", result.getValue());
    return "chat?faces-redirect=true";
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
